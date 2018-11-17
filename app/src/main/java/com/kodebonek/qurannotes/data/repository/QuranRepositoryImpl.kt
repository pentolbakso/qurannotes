package com.kodebonek.qurannotes.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.kodebonek.qurannotes.data.db.AppDatabase
import com.kodebonek.qurannotes.data.entity.Ayah
import com.kodebonek.qurannotes.data.entity.Edition
import com.kodebonek.qurannotes.data.entity.Resource
import com.kodebonek.qurannotes.data.entity.Surah
import com.kodebonek.qurannotes.data.remote.ApiResponse
import com.kodebonek.qurannotes.data.remote.ApiService
import com.kodebonek.qurannotes.data.remote.wrapper.CompleteQuranResponse
import com.kodebonek.qurannotes.util.DataHelper
import kotlinx.coroutines.experimental.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import rx.Observable
import timber.log.Timber

class QuranRepositoryImpl(private val apiService: ApiService,
                          private val appDatabase: AppDatabase): QuranRepository {

    override fun verifyQuranDatabase(): LiveData<Resource<Boolean>> {
        val liveData = MutableLiveData<Resource<Boolean>>()
        liveData.postValue(Resource.loading())
        launch {
            val count = appDatabase.quranDao().getSurahCount()
            //114 = number of surah in Quran
            liveData.postValue(Resource.success(count == 114))
        }
        return liveData
    }

    override fun getQuranEditions(): LiveData<Resource<List<Edition>>> {
        Timber.d("getQuranEditions")

        val liveData = MutableLiveData<Resource<List<Edition>>>()
        liveData.postValue(Resource.loading())

        val call = apiService.getQuranEditions()
        call.enqueue(object: Callback<ApiResponse<List<Edition>>> {
            override fun onFailure(call: Call<ApiResponse<List<Edition>>>?, t: Throwable?) {
                liveData.postValue(Resource.error(t?.message))
            }

            override fun onResponse(call: Call<ApiResponse<List<Edition>>>?, response: Response<ApiResponse<List<Edition>>>) {
                if (response.isSuccessful) {
                    val allowedLanguage = arrayOf("id","en","es","pt","fr")
                    val filtered = response.body()?.data?.filter {
                        allowedLanguage.indexOf(it.language.orEmpty()) >= 0
                    }
                    liveData.postValue(Resource.success(filtered))
                } else
                    liveData.postValue(Resource.error(DataHelper.getErrorMessage(response.errorBody())))
            }
        })
        return liveData
    }

    override fun downloadQuranTranslation(edition: String): LiveData<Resource<List<Surah>>> {
        Timber.d("downloadQuranTranslation edition: ${edition}")

        val liveData = MutableLiveData<Resource<List<Surah>>>()
        liveData.postValue(Resource.loading())

        val call = apiService.getCompleteQuran(edition)
        call.enqueue(object : Callback<ApiResponse<CompleteQuranResponse>> {
            override fun onFailure(call: Call<ApiResponse<CompleteQuranResponse>>?, t: Throwable?) {
                liveData.postValue(Resource.error(t?.message))
            }

            override fun onResponse(call: Call<ApiResponse<CompleteQuranResponse>>?, response: Response<ApiResponse<CompleteQuranResponse>>) {
                if (response.isSuccessful) {
                    val surahs = response.body()?.data?.surahs
                    surahs?.forEach {
                        val surahNumber = it.number
                        it.ayahs?.forEach {
                            it.surahNumber = surahNumber!!
                        }
                    }
                    liveData.postValue(Resource.success(surahs))
                } else
                    liveData.postValue(Resource.error(DataHelper.getErrorMessage(response.errorBody())))
            }
        })
        return liveData
    }

    override fun downloadQuranArabicAndSave(edition: String, translations: List<Surah>): LiveData<Resource<Boolean>> {
        Timber.d("downloadQuranArabicAndSave edition: ${edition}")

        val liveData = MutableLiveData<Resource<Boolean>>()
        liveData.postValue(Resource.loading())

        val call = apiService.getCompleteQuran(edition)
        call.enqueue(object : Callback<ApiResponse<CompleteQuranResponse>> {
            override fun onFailure(call: Call<ApiResponse<CompleteQuranResponse>>?, t: Throwable?) {
                liveData.postValue(Resource.error(t?.message))
            }

            override fun onResponse(call: Call<ApiResponse<CompleteQuranResponse>>?, response: Response<ApiResponse<CompleteQuranResponse>>) {
                if (response.isSuccessful) {
                    launch {
                        //merge arabic and translation
                        val ayahs = ArrayList<Ayah>()
                        translations?.forEach {
                            ayahs.addAll(it.ayahs!!)
                        }

                        val arabicSurahs = response.body()?.data?.surahs
                        val arabics = ArrayList<String>()
                        arabicSurahs?.forEach {
                            it.ayahs?.forEach {
                                arabics.add(it.text!!)
                            }
                        }

                        if (ayahs.size !== arabics.size) {
                            liveData.postValue(Resource.error("Invalid number of ayahs, please redownload"))
                            return@launch
                        }

                        //update arabic field
                        var i = 0;
                        ayahs.forEach{
                            it.arabic = arabics[i]
                            i++
                        }

                        appDatabase.quranDao().clear()
                        appDatabase.ayahDao().clear()

                        //save to db
                        appDatabase.quranDao().addAll(translations)
                        appDatabase.ayahDao().addAll(ayahs)
                        liveData.postValue(Resource.success(true))
                    }
                } else
                    liveData.postValue(Resource.error(DataHelper.getErrorMessage(response.errorBody())))
            }
        })
        return liveData
    }

    override fun getSurahs(): LiveData<Resource<List<Surah>>> {
        Timber.d("getSurahs")

        val liveData = MutableLiveData<Resource<List<Surah>>>()
        liveData.postValue(Resource.loading())

        launch {
            val surahs = appDatabase.quranDao().getSurahs()
            liveData.postValue(Resource.success(surahs))
        }
        return liveData
    }

    override fun getAyahs(surahNumber: Int): LiveData<Resource<List<Ayah>>> {
        Timber.d("getAyahs surah:${surahNumber}")

        val liveData = MutableLiveData<Resource<List<Ayah>>>()
        liveData.postValue(Resource.loading())

        launch {
            val ayahs = appDatabase.quranDao().getAyahs(surahNumber)
            Timber.d("getAyah result : ${ayahs.size}")
            liveData.postValue(Resource.success(ayahs))
        }
        return liveData
    }


}
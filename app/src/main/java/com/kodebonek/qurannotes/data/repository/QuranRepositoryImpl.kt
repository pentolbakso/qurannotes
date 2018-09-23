package com.kodebonek.qurannotes.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.kodebonek.qurannotes.data.db.AppDatabase
import com.kodebonek.qurannotes.data.entity.Edition
import com.kodebonek.qurannotes.data.entity.Resource
import com.kodebonek.qurannotes.data.entity.Surah
import com.kodebonek.qurannotes.data.remote.ApiResponse
import com.kodebonek.qurannotes.data.remote.ApiService
import com.kodebonek.qurannotes.util.DataHelper
import kotlinx.coroutines.experimental.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class QuranRepositoryImpl(private val apiService: ApiService,
                          private val appDatabase: AppDatabase): QuranRepository {

    override fun verifyQuranDatabase(): LiveData<Resource<Boolean>> {
        val liveData = MutableLiveData<Resource<Boolean>>()
        liveData.postValue(Resource.loading())
        launch {
            val count = appDatabase.quranDao().getSurahCount()
            //114 = number of ayah in Quran
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
                if (response.isSuccessful)
                    liveData.postValue(Resource.success(response.body()?.data))
                else
                    liveData.postValue(Resource.error(DataHelper.getErrorMessage(response.errorBody())))
            }
        })
        return liveData
    }

    override fun downloadQuran(edition: String): LiveData<Resource<Boolean>> {
        Timber.d("downloadQuran edition:" + edition)

        val liveData = MutableLiveData<Resource<Boolean>>()
        liveData.postValue(Resource.loading())

        val call = apiService.getCompleteQuran(edition)
        call.enqueue(object : Callback<ApiResponse<List<Surah>>> {
            override fun onFailure(call: Call<ApiResponse<List<Surah>>>?, t: Throwable?) {
                liveData.postValue(Resource.error(t?.message))
            }

            override fun onResponse(call: Call<ApiResponse<List<Surah>>>?, response: Response<ApiResponse<List<Surah>>>) {
                if (response.isSuccessful) {
                    //TODO inject to database
                    liveData.postValue(Resource.success(true))
                } else
                    liveData.postValue(Resource.error(DataHelper.getErrorMessage(response.errorBody())))
            }
        })
        return liveData
    }

}
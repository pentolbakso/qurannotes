package com.kodebonek.qurannotes.ui.download

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import com.kodebonek.qurannotes.data.entity.Edition
import com.kodebonek.qurannotes.data.entity.Resource
import com.kodebonek.qurannotes.data.entity.Status
import com.kodebonek.qurannotes.data.repository.QuranRepository
import javax.inject.Inject

class SelectEditionViewModel
@Inject internal constructor(private val quranRepository: QuranRepository): ViewModel() {

    val selectedEdition = MutableLiveData<Edition>()

    fun setSelected(edition: Edition?) {
        selectedEdition.postValue(edition)
    }

    fun getQuranEditions(): LiveData<Resource<List<Edition>>> {
        return quranRepository.getQuranEditions()
    }

    fun downloadQuran(): LiveData<Resource<Boolean>> {
        val mediator = MediatorLiveData<Resource<Boolean>>()
        mediator.postValue(Resource.loading())

        val id = selectedEdition.value?.identifier
        val download = quranRepository.downloadQuranTranslation(id!!)
        mediator.addSource(download, {
            if (it?.status == Status.SUCCESS) {
                mediator.removeSource(download)

                val translations = it?.data
                val process = quranRepository.downloadQuranArabicAndSave("quran-uthmani", translations!!)
                mediator.addSource(process, {
                    if (it?.status != Status.LOADING) {
                        mediator.removeSource(process)
                        if (it?.status == Status.ERROR)
                            mediator.postValue(Resource.error(it?.message))
                        else
                            mediator.postValue(Resource.success(true))
                    }
                })

            } else if (it?.status === Status.ERROR) {
                mediator.removeSource(download)
                mediator.postValue(Resource.error(it?.message))
            }
        })
        return mediator
    }
}
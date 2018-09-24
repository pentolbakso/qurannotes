package com.kodebonek.qurannotes.ui.download

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.kodebonek.qurannotes.data.entity.Edition
import com.kodebonek.qurannotes.data.entity.Resource
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
        val id = selectedEdition.value?.identifier
        return quranRepository.downloadQuran(id!!)
    }
}
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

    fun getQuranEditions(): LiveData<Resource<List<Edition>>> {
        return quranRepository.getQuranEditions()
    }

    fun downloadQuran(edition: String): LiveData<Resource<Boolean>> {
        return quranRepository.downloadQuran(edition)
    }
}
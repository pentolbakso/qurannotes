package com.kodebonek.qurannotes.ui.surah

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.kodebonek.qurannotes.data.entity.Resource
import com.kodebonek.qurannotes.data.entity.Surah
import com.kodebonek.qurannotes.data.repository.QuranRepository
import javax.inject.Inject

class SurahListViewModel
@Inject internal constructor(private val quranRepository: QuranRepository): ViewModel() {

    fun getSurahs(): LiveData<Resource<List<Surah>>> {
        return quranRepository.getSurahs()
    }
}
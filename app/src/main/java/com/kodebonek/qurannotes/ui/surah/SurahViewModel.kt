package com.kodebonek.qurannotes.ui.surah

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.kodebonek.qurannotes.data.entity.Ayah
import com.kodebonek.qurannotes.data.entity.Resource
import com.kodebonek.qurannotes.data.repository.QuranRepository
import javax.inject.Inject

class SurahViewModel
@Inject internal constructor(private val quranRepository: QuranRepository): ViewModel() {

    fun getAyahs(surahNumber: Int): LiveData<Resource<List<Ayah>>> {
        return quranRepository.getAyahs(surahNumber)
    }
}
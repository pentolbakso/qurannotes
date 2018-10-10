package com.kodebonek.qurannotes.ui.surah

import android.arch.lifecycle.ViewModel
import com.kodebonek.qurannotes.data.repository.QuranRepository
import javax.inject.Inject

class SurahViewModel
@Inject internal constructor(private val quranRepository: QuranRepository): ViewModel() {
}
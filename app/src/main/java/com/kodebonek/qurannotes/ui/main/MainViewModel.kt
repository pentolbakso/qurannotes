package com.kodebonek.qurannotes.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.kodebonek.qurannotes.data.repository.QuranRepository
import javax.inject.Inject

/**
 * Created by Potencio on 7/18/2017. @ 11:07 AM
 * For android
 */

class MainViewModel @Inject
internal constructor(private val quranRepository: QuranRepository) : ViewModel() {

}

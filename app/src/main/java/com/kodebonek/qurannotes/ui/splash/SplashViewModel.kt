package com.kodebonek.qurannotes.ui.splash

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.kodebonek.qurannotes.data.entity.Resource
import com.kodebonek.qurannotes.data.repository.QuranRepository
import javax.inject.Inject

class SplashViewModel
@Inject internal constructor(private val quranRepository: QuranRepository): ViewModel() {

    fun isQuranDatabaseReady(): LiveData<Resource<Boolean>> {
        return quranRepository.verifyQuranDatabase()
    }
}
package com.kodebonek.qurannotes.data.repository

import android.arch.lifecycle.LiveData
import com.kodebonek.qurannotes.data.entity.Edition
import com.kodebonek.qurannotes.data.entity.Resource

interface QuranRepository {

    fun verifyQuranDatabase(): LiveData<Resource<Boolean>>
    fun getQuranEditions(): LiveData<Resource<List<Edition>>>
    fun downloadQuran(edition: String): LiveData<Resource<Boolean>>
}
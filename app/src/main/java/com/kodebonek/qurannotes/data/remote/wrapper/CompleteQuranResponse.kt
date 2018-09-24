package com.kodebonek.qurannotes.data.remote.wrapper

import com.kodebonek.qurannotes.data.entity.Edition
import com.kodebonek.qurannotes.data.entity.Surah

data class CompleteQuranResponse (
        val surahs: List<Surah>,
        val edition: Edition
)

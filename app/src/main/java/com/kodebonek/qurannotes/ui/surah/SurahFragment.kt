package com.kodebonek.qurannotes.ui.surah

import android.arch.lifecycle.ViewModelProviders
import com.kodebonek.qurannotes.R
import com.kodebonek.qurannotes.ui.base.BaseFragment

class SurahFragment: BaseFragment() {

    private lateinit var viewModel: SurahViewModel

    override val layoutId: Int = R.layout.fragment_surah

    override fun initializeAfterCreated() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SurahViewModel::class.java)
    }
}
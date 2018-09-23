package com.kodebonek.qurannotes.ui.download

import android.os.Bundle
import com.kodebonek.qurannotes.R
import com.kodebonek.qurannotes.ui.base.BaseActivity
import kotlinx.android.synthetic.main.app_toolbar.*

class DownloadActivity: BaseActivity() {

    override val layoutId: Int = R.layout.activity_download

    override fun initializeActivity(savedInstanceState: Bundle?) {

        setSupportActionBar(toolbar)

        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SelectEditionFragment.newInstance())
                .commit()
    }
}
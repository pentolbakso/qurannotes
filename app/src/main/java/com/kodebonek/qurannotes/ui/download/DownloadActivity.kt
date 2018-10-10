package com.kodebonek.qurannotes.ui.download

import android.content.Intent
import android.os.Bundle
import com.kodebonek.qurannotes.R
import com.kodebonek.qurannotes.ui.base.BaseActivity
import com.kodebonek.qurannotes.ui.main.MainActivity
import kotlinx.android.synthetic.main.app_toolbar.*

class DownloadActivity: BaseActivity() {

    override val layoutId: Int = R.layout.activity_download

    override fun initializeActivity(savedInstanceState: Bundle?) {

        setSupportActionBar(toolbar)

        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SelectEditionFragment.newInstance())
                .commit()
    }

    fun goToMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

}
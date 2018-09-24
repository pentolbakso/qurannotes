package com.kodebonek.qurannotes.ui.splash

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.kodebonek.qurannotes.R
import com.kodebonek.qurannotes.data.entity.Status
import com.kodebonek.qurannotes.ui.base.BaseActivity
import com.kodebonek.qurannotes.ui.download.DownloadActivity
import com.kodebonek.qurannotes.ui.main.MainActivity

class SplashActivity: BaseActivity() {

    private lateinit var viewModel: SplashViewModel;

    override val layoutId: Int = R.layout.activity_splash

    override fun initializeActivity(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SplashViewModel::class.java)

        Handler().postDelayed({ verifyDatabase() }, 1000)
    }

    private fun verifyDatabase() {
        viewModel.isQuranDatabaseReady().observe(this, Observer {
            when(it?.status) {
                Status.LOADING -> {}
                Status.ERROR -> showError(it?.message)
                Status.SUCCESS -> {
                    if (it?.data!!)
                        goToMain()
                    else
                        goToDownload()
                }
            }
        })
    }

    fun goToDownload() {
        val intent = Intent(this, DownloadActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    fun goToMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

}
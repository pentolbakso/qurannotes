package com.kodebonek.qurannotes.ui.base

import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.djakartalloyd.dlmarket.util.ProgressHelper
import com.kodebonek.qurannotes.R
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.app_toolbar.*
import kotlinx.android.synthetic.main.app_toolbar.view.*
import kotlinx.android.synthetic.main.design_layout_snackbar_include.view.*
import timber.log.Timber
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {

    private var progress: ProgressHelper? = null

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> {
        return fragmentInjector
    }

    abstract val layoutId: Int

    abstract fun initializeActivity(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        progress = ProgressHelper(this)

        initializeActivity(savedInstanceState)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    open fun pageTitle(title: String, subtitle: String? = null) {
        if (toolbar == null) return
        toolbar.tvTitle.text = title
        if (subtitle != null) {
            toolbar.tvSubtitle.visibility = View.VISIBLE
            toolbar.tvSubtitle.text = subtitle
        } else
            toolbar.tvSubtitle.visibility = View.GONE
    }

    open fun showInfo(message: String?) {
        if (message.isNullOrEmpty()) return
        val snackbar = Snackbar.make(findViewById(android.R.id.content) , message!!, Snackbar.LENGTH_LONG)
        snackbar.setAction(resources.getString(R.string.close), { snackbar.dismiss() })
        snackbar.show()
    }

    open fun showError(message: String?) {
        if (message.isNullOrEmpty()) return
        val snackbar = Snackbar.make(findViewById(android.R.id.content) , message!!, Snackbar.LENGTH_LONG)
        snackbar.view.setBackgroundColor(resources.getColor(R.color.red))
        snackbar.view.snackbar_text.setTextColor(resources.getColor(R.color.white))
        snackbar.setAction(resources.getString(R.string.close), { snackbar.dismiss() })
        snackbar.setActionTextColor(Color.WHITE)
        snackbar.show()
        Timber.e(message)
    }

    open fun showSuccess(message: String?) {
        if (message.isNullOrEmpty()) return
        val snackbar = Snackbar.make(findViewById(android.R.id.content) , message!!, Snackbar.LENGTH_LONG)
        snackbar.view.setBackgroundColor(resources.getColor(R.color.green))
        snackbar.view.snackbar_text.setTextColor(resources.getColor(R.color.white))
        snackbar.setAction(resources.getString(R.string.close), { snackbar.dismiss() })
        snackbar.setActionTextColor(Color.WHITE)
        snackbar.show()
    }

    open fun showProgress(message: String? = null) {
        progress?.show(message)
    }

    open fun dismissProgress() {
        progress?.dismiss()
    }
}
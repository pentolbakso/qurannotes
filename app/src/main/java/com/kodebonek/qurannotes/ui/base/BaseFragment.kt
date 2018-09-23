package com.kodebonek.qurannotes.ui.base

import android.app.Activity
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.djakartalloyd.dlmarket.util.ProgressHelper
import com.kodebonek.qurannotes.R
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.design_layout_snackbar_include.view.*
import timber.log.Timber

abstract class BaseFragment : Fragment() {

    private var progress: ProgressHelper? = null

    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    abstract val layoutId: Int

    abstract fun initializeAfterCreated()

    override fun onCreate(savedInstanceState: Bundle?) {
        progress = ProgressHelper(activity as Activity)

        super.onCreate(savedInstanceState)
        Timber.d("onCreate")

        viewModelFactory = (activity as BaseActivity).viewModelFactory
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("onDestroy")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.background = resources.getDrawable(R.color.white)
        initializeAfterCreated()
    }

    override fun onPause() {
        super.onPause()
        Timber.d("onPause")
    }

    override fun onResume() {
        super.onResume()
        Timber.d("onResume")
    }

    open fun showInfo(message: String?) {
        if (message.isNullOrEmpty()) return
        val snackbar = Snackbar.make(view!! , message!!, Snackbar.LENGTH_LONG)
        snackbar.setAction(resources.getString(R.string.close), { snackbar.dismiss() })
        snackbar.show()
    }

    open fun showSuccess(message: String?) {
        if (message.isNullOrEmpty()) return
        val snackbar = Snackbar.make(view!! , message!!, Snackbar.LENGTH_LONG)
        snackbar.view.setBackgroundColor(resources.getColor(R.color.green))
        snackbar.view.snackbar_text.setTextColor(resources.getColor(R.color.white))
        snackbar.setAction(resources.getString(R.string.close), { snackbar.dismiss() })
        snackbar.setActionTextColor(Color.WHITE)
        snackbar.show()
    }

    open fun showError(message: String?) {
        if (message.isNullOrEmpty()) return

        //Toast.makeText(activity, message!!, Toast.LENGTH_LONG).show()
        val snackbar = Snackbar.make(view!! , message!!, Snackbar.LENGTH_LONG)
        snackbar.view.setBackgroundColor(resources.getColor(R.color.red))
        snackbar.view.snackbar_text.setTextColor(resources.getColor(R.color.white))
        snackbar.setAction(resources.getString(R.string.close), { snackbar.dismiss() })
        snackbar.setActionTextColor(Color.WHITE)
        snackbar.show()
        Timber.e(message)
    }

    open fun showProgress(message: String?) {
        progress?.show(message)
        //(activity as BaseActivity).showProgress(message)
    }

    open fun dismissProgress() {
        progress?.dismiss()
        //(activity as BaseActivity).dismissProgress()
    }

}
package com.kodebonek.qurannotes.ui.download

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.kodebonek.qurannotes.R
import com.kodebonek.qurannotes.data.entity.Edition
import com.kodebonek.qurannotes.data.entity.Status
import com.kodebonek.qurannotes.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_select_edition.*

class SelectEditionFragment: BaseFragment() {

    companion object {
        fun newInstance(): SelectEditionFragment {
            val fragment = SelectEditionFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override val layoutId: Int = R.layout.fragment_select_edition

    private lateinit var viewModel: SelectEditionViewModel

    private val adapter: EditionAdapter = EditionAdapter({ onItemClick(it) })

    override fun initializeAfterCreated() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SelectEditionViewModel::class.java)

        activity?.title = "Select Quran Edition"

        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        viewModel.setSelected(null)
        viewModel.selectedEdition.observe(this, Observer {
            btnDownload.isEnabled = it !== null
            btnDownload.alpha = if (it !== null) 1f else .3f
        })
        btnDownload.setOnClickListener {
            startDownload()
        }

        swipeRefreshLayout.setOnRefreshListener {
            adapter.reset()
            viewModel.setSelected(null)
            swipeRefreshLayout.isRefreshing = false
            loadEditions()
        }

        loadEditions()
    }

    private fun loadEditions() {
        viewModel.getQuranEditions().observe(this, Observer {
            when(it?.status) {
                Status.LOADING -> { showProgress(resources.getString(R.string.loading)) }
                Status.ERROR -> {
                    dismissProgress()
                    showError(it?.message)
                }
                Status.SUCCESS -> {
                    dismissProgress()
                    adapter.addItems(it?.data!!)
                }
            }
        })
    }

    private fun startDownload() {
        viewModel.downloadQuran().observe(this, Observer {
            when(it?.status) {
                Status.LOADING -> { showProgress(resources.getString(R.string.downloading)) }
                Status.ERROR -> {
                    dismissProgress()
                    showError(it?.message)
                }
                Status.SUCCESS -> {
                    dismissProgress()
                    goToMain()
                }
            }
        })
    }

    private fun goToMain() {
        (activity as DownloadActivity).goToMain()
    }

    private fun onItemClick(edition: Edition) {
        viewModel.setSelected(edition)
    }

}
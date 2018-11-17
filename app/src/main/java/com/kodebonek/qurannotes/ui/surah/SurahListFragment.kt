package com.kodebonek.qurannotes.ui.surah

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.kodebonek.qurannotes.R
import com.kodebonek.qurannotes.data.entity.Status
import com.kodebonek.qurannotes.data.entity.Surah
import com.kodebonek.qurannotes.ui.base.BaseFragment
import com.kodebonek.qurannotes.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_surah_list.*

class SurahListFragment: BaseFragment() {

    companion object {
        fun newInstance(): SurahListFragment {
            val fragment = SurahListFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModel: SurahListViewModel

    override val layoutId: Int = R.layout.fragment_surah_list

    private val listAdapter: SurahListAdapter = SurahListAdapter({ onSurahClick(it) })

    override fun initializeAfterCreated() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SurahListViewModel::class.java)

        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)
        recyclerView.adapter = listAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        loadSurahs()
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).pageTitle(resources.getString(R.string.app_name))
    }

    private fun loadSurahs() {
        viewModel.getSurahs().observe(this, Observer {
            when(it?.status) {
                Status.LOADING -> { }
                Status.ERROR -> { showError(it?.message) }
                Status.SUCCESS -> {
                    listAdapter.addItems(it?.data!!)
                }
            }
        })
    }

    private fun onSurahClick(surah: Surah) {
        //showInfo(surah.englishName)
        (activity as MainActivity).addFragment(SurahFragment.newInstance(surah), true)
    }

}
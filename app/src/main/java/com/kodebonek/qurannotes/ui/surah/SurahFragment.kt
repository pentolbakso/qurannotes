package com.kodebonek.qurannotes.ui.surah

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.kodebonek.qurannotes.R
import com.kodebonek.qurannotes.data.entity.Ayah
import com.kodebonek.qurannotes.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_select_edition.*

class SurahFragment: BaseFragment() {

    companion object {
        fun newInstance(): SurahFragment {
            val fragment = SurahFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModel: SurahViewModel

    override val layoutId: Int = R.layout.fragment_surah

    private val listAdapter: AyahListAdapter = AyahListAdapter({ onAyahClick(it) })

    override fun initializeAfterCreated() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SurahViewModel::class.java)

        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)
        recyclerView.adapter = listAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        loadAyahs()
    }

    private fun loadAyahs() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun onAyahClick(ayah: Ayah) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
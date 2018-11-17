package com.kodebonek.qurannotes.ui.surah

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.kodebonek.qurannotes.R
import com.kodebonek.qurannotes.data.entity.Ayah
import com.kodebonek.qurannotes.data.entity.Status
import com.kodebonek.qurannotes.data.entity.Surah
import com.kodebonek.qurannotes.ui.base.BaseFragment
import com.kodebonek.qurannotes.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_surah.*

class SurahFragment: BaseFragment() {

    companion object {
        fun newInstance(surah: Surah): SurahFragment {
            val fragment = SurahFragment()
            val args = Bundle()
            args.putParcelable("surah", surah)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModel: SurahViewModel

    override val layoutId: Int = R.layout.fragment_surah

    private val listAdapter: AyahListAdapter = AyahListAdapter({ onAyahClick(it) })

    private lateinit var surah: Surah

    override fun initializeAfterCreated() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SurahViewModel::class.java)

        surah = arguments?.getParcelable("surah")!!

        setHasOptionsMenu(true)

        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)
        recyclerView.adapter = listAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        loadAyahs()
    }

    override fun onResume() {
        super.onResume()
        updateTitle(listAdapter.itemCount)
    }

    fun updateTitle(ayahCount: Int) {
        var subtitle = surah.englishNameTranslation
        if (ayahCount>0)
            subtitle = "${subtitle} (${ayahCount} ayahs)"
        (activity as MainActivity).pageTitle("${surah.number}. ${surah.englishName.orEmpty().toUpperCase()}", subtitle)
    }

    private fun loadAyahs() {
        viewModel.getAyahs(surah.number!!).observe(this, Observer {
            when(it?.status) {
                Status.LOADING -> { }
                Status.ERROR -> { showError(it?.message) }
                Status.SUCCESS -> {
                    listAdapter.addItems(it?.data!!)
                    updateTitle(listAdapter.itemCount)
                }
            }
        })
    }

    private fun onAyahClick(ayah: Ayah) {
        showInfo("clicked on ayah ${ayah.numberInSurah}")
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_surah, menu);
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if (id == R.id.action_text_settings) {
            showInfo("Text settins")
        }
        return super.onOptionsItemSelected(item)
    }

}
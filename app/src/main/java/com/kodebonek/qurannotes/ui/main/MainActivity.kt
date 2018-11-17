package com.kodebonek.qurannotes.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.View
import com.kodebonek.qurannotes.R
import com.kodebonek.qurannotes.ui.base.BaseActivity
import com.kodebonek.qurannotes.ui.surah.SurahListFragment
import com.kodebonek.qurannotes.util.UiHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_toolbar.*
import kotlinx.android.synthetic.main.app_toolbar.view.*

class MainActivity : BaseActivity(), FragmentManager.OnBackStackChangedListener {

    private lateinit var mainViewModel: MainViewModel

    override val layoutId: Int = R.layout.activity_main

    override fun initializeActivity(savedInstanceState: Bundle?) {
        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        setSupportActionBar(toolbar)

        bottomNavigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        bottomNavigation.inflateMenu(R.menu.bottom_navigation_main)
        bottomNavigation.selectedItemId = R.id.action_surahs

        btnBack.setOnClickListener {
            supportFragmentManager.popBackStack()
        }
        supportFragmentManager.addOnBackStackChangedListener(this)

        //always show icon+text
        UiHelper.disableShiftMode(bottomNavigation)
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        //popToFirstFragment()

        when (item.itemId) {
            R.id.action_surahs -> {
                replaceFragment(SurahListFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_search -> {
                //replaceFragment(MyVesselsFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_notes -> {
                //replaceFragment(MyCargosFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_settings -> {
                //replaceFragment(DiscussionFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            else -> false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.removeOnBackStackChangedListener(this)
    }

    override fun onBackStackChanged() {
        if (supportFragmentManager.backStackEntryCount>0) {
            btnBack.visibility = View.VISIBLE
        } else {
            btnBack.visibility = View.GONE
            pageTitle(resources.getString(R.string.app_name))
        }
    }

    fun replaceFragment(fragment: Fragment, addToBackstack: Boolean = false) {
        if (addToBackstack)
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit()
        else
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit()
    }

    fun addFragment(fragment: Fragment, addToBackstack: Boolean = false) {
        if (addToBackstack)
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit()
        else
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit()
    }

    fun popToFirstFragment() {
        while (supportFragmentManager.getBackStackEntryCount() >= 1) {
            supportFragmentManager.popBackStackImmediate()
        }
    }
}

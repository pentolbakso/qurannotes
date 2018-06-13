package com.kodebonek.qurannotes.ui


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.kodebonek.qurannotes.data.DataSource
import com.kodebonek.qurannotes.data.db.entity.User
import javax.inject.Inject

/**
 * Created by Potencio on 7/18/2017. @ 11:07 AM
 * For android
 */

class MainViewModel @Inject
internal constructor(private val dataSource: DataSource) : ViewModel() {


    fun loadUsers(): LiveData<List<User>> {
        return dataSource.loadUsers()
    }

}

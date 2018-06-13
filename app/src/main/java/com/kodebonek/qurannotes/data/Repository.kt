package com.kodebonek.qurannotes.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.kodebonek.qurannotes.data.db.entity.User
import com.kodebonek.qurannotes.data.remote.AppApiService

/**
 * @author <@Po10cio> on 10/4/17 for KotlinDagger
 *
 */
open class Repository(val apiService: AppApiService) : DataSource {

    /**
     * Loads s list fo users
     * */
    override fun loadUsers(): LiveData<List<User>> {
        return Transformations.map(apiService.getUsers(), { response -> response.body })

    }

}
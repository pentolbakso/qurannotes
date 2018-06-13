package com.kodebonek.qurannotes.data

import android.arch.lifecycle.LiveData
import com.kodebonek.qurannotes.data.db.entity.User

/**
 * @author <@Po10cio> on 10/4/17 for KotlinDagger
 *
 *

 */

interface DataSource {
    /**
     * Loads a list of users
     * */
    fun loadUsers(): LiveData<List<User>>
}

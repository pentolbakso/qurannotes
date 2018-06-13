package com.kodebonek.qurannotes.data.remote

import android.arch.lifecycle.LiveData
import com.kodebonek.qurannotes.data.db.entity.User
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author <@Po10cio> on 11/3/17 for AndroidKotlinBoilerplate
 *
 */
interface AppApiService {

    /**
     * Returns a list of users from the server
     * */
    @GET("users")
    fun getUsers(): LiveData<ApiResponse<List<User>>>


    /**
     * Returns a user given the uui
     * */
    @GET("users/{uuid}")
    fun getUser(@Path("uuid") userId: String): LiveData<ApiResponse<User>>

}
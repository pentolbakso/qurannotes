package com.kodebonek.qurannotes.data.remote

import com.kodebonek.qurannotes.data.entity.Edition
import com.kodebonek.qurannotes.data.entity.Surah
import com.kodebonek.qurannotes.data.remote.wrapper.CompleteQuranResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    companion object {
        const val BASE_URL: String = "http://api.alquran.cloud/"
    }

    @GET("edition?format=text&type=translation")
    fun getQuranEditions(): Call<ApiResponse<List<Edition>>>

    @GET("quran/{edition}")
    fun getCompleteQuran(@Path("edition") edition: String): Call<ApiResponse<CompleteQuranResponse>>

}
package com.kodebonek.qurannotes.data.remote

import com.google.gson.annotations.SerializedName

class ApiResponse<T> {

    @SerializedName("code")
    val code: Int? = null

    @SerializedName("status")
    val status: String? = ""

    @SerializedName("data")
    val data: T? = null
}
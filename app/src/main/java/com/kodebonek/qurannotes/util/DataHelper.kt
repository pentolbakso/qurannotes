package com.kodebonek.qurannotes.util

import com.google.gson.Gson
import com.kodebonek.qurannotes.data.remote.wrapper.ErrorData
import okhttp3.ResponseBody

class DataHelper {

    companion object {
        fun getErrorMessage(errorBody: ResponseBody?): String? {
            try {
                val string = errorBody?.string()
                val error = Gson().fromJson(string, ErrorData::class.java)
                return error.data

            } catch (e: Exception){
                e.printStackTrace()
                return e.message
            }
        }
    }

}
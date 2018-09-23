package com.djakartalloyd.dlmarket.util

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper(context: Context, name: String) {

    val pref: SharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    val KEY_TOKEN: String = "accesstoken"
    val KEY_FIRST_TIME: String = "firsttime"

    fun saveToken(token: String?) {
        pref.edit().putString(KEY_TOKEN, token).apply()
    }

    fun getToken(): String? {
        return pref.getString(KEY_TOKEN, null)
    }

    fun removeToken() {
        pref.edit().remove(KEY_TOKEN).apply()
    }

    fun getFirstTime(): Boolean {
        return pref.getBoolean(KEY_FIRST_TIME, true)
    }

    fun setFirstTime(flag: Boolean) {
        pref.edit().putBoolean(KEY_FIRST_TIME, flag).apply()
    }

}
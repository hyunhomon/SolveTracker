package com.example.solvetracker.data

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREF_NAME = "preferences"
        private const val KEY_USER_HANDLE = "user_handle"
    }

    fun getUserHandle(): String {
        return sharedPreferences.getString(KEY_USER_HANDLE, "") ?: ""
    }
    fun setUserHandle(userHandle: String) {
        sharedPreferences.edit().putString(KEY_USER_HANDLE, userHandle).apply()
    }
}
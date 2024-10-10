package com.example.solvetracker

import android.app.Application

class Provider: Application() {
    private val pref = getSharedPreferences("sp1", MODE_PRIVATE)
    private val editor = pref.edit()
    private val isFirstKey = "isFirst"
    private val userHandleKey = "userHandle"

    var isFirst = true
    var userHandle = ""

    fun setIsFirst(value: Boolean) {
        editor.putBoolean(isFirstKey, value)
        editor.commit()
    }
    fun setUserHandle(value: String) {
        editor.putString(userHandleKey, value)
        editor.commit()
    }

    override fun onCreate() {
        super.onCreate()
        isFirst = pref.getBoolean(isFirstKey, true)
        userHandle = pref.getString(userHandleKey, "") ?: ""
    }
}
package com.example.solvetracker

import android.app.Application
import android.content.SharedPreferences

class Provider: Application() {
    private lateinit var pref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private val userHandleKey = "userHandle"

    companion object {
        var userHandle = ""
    }

    fun setUserHandle(value: String) {
        editor.putString(userHandleKey, value)
        editor.apply()
        userHandle = value
    }

    override fun onCreate() {
        super.onCreate()

        pref = getSharedPreferences("sp1", MODE_PRIVATE)
        editor = pref.edit()
        userHandle = pref.getString(userHandleKey, "") ?: ""
    }
}
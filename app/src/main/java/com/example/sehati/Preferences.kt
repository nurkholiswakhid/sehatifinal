package com.example.sehati

import android.content.Context
import android.content.SharedPreferences


class Preferences(context: Context) {
    private val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences("Token Pref", Context.MODE_PRIVATE)
    }


    fun saveSession() {
        sharedPreferences.edit().putBoolean("session", true).apply()
    }

    val session get() =  sharedPreferences.getBoolean("session", false)

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }
}
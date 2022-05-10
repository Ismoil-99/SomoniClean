package com.example.somoni.extensions

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.somoni.extensions.utils.APP_SETTINGS

class SomoniApp : Application() {

    override fun onCreate() {
        super.onCreate()
        sharedPreferences = this.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)
        sharedPreferencesEditor = sharedPreferences.edit()
    }
    companion object {
        lateinit var sharedPreferences: SharedPreferences
        lateinit var sharedPreferencesEditor: SharedPreferences.Editor
    }
}
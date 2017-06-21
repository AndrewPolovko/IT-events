package com.epam.androidlab.it_events

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import com.squareup.leakcanary.LeakCanary

class MyApp : Application() {
    companion object {
        lateinit var mAppContext: Context

        fun isNetworkAvailable(): Boolean {
            val connectivityManager = mAppContext.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
        }
    }

    override fun onCreate() {
        super.onCreate()
        mAppContext = this
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }
}
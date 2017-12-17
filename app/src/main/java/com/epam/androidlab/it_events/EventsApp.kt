package com.epam.androidlab.it_events

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import com.epam.androidlab.it_events.data.network.EpamApi

class EventsApp : Application() {
    companion object {
        lateinit var mAppContext: Context
        val mEpamApi: EpamApi by lazy { EpamApi() }

        fun isNetworkAvailable(): Boolean {
            val connectivityManager = mAppContext.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
        }
    }

    override fun onCreate() {
        super.onCreate()
        mAppContext = this
    }
}
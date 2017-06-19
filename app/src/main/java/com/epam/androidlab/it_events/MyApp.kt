package com.epam.androidlab.it_events

import android.app.Application
import android.content.Context
import com.squareup.leakcanary.LeakCanary

class MyApp : Application() {
    companion object {
        lateinit var mAppContext: Context
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
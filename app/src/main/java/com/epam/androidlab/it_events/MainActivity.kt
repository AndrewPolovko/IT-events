package com.epam.androidlab.it_events

import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.epam.androidlab.it_events.models.EPAM_Events_Response


class MainActivity : AppCompatActivity() {
    lateinit var mRecyclerView: RecyclerView
    lateinit var eventsAdapter: EPAM_Events_Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRecyclerView = findViewById(R.id.recycler_view) as RecyclerView

        if (isNetworkAvailable())
            EPAM_Events_Manager().getEvents(object : EPAM_Events_Manager.EPAM_Events_Callback {
                override fun onSuccess(events: EPAM_Events_Response?) {
                    events?.let {
                        eventsAdapter = EPAM_Events_Adapter(events)
                        mRecyclerView.adapter = eventsAdapter
                        mRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                        mRecyclerView.itemAnimator = DefaultItemAnimator()
                    }
                }

                override fun onFail(t: Throwable) {
                    Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
                }
            })
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
    }
}

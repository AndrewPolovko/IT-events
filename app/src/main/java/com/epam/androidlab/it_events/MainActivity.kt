package com.epam.androidlab.it_events

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.epam.androidlab.it_events.data.EPAM_Events_Adapter
import com.epam.androidlab.it_events.data.EPAM_Events_Manager
import com.epam.androidlab.it_events.data.models.EPAM_Events_Response


class MainActivity : AppCompatActivity() {
    lateinit var mRecyclerView: RecyclerView
    lateinit var eventsAdapter: EPAM_Events_Adapter
    lateinit var eventsManager: EPAM_Events_Manager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRecyclerView = findViewById(R.id.recycler_view) as RecyclerView

        eventsManager = EPAM_Events_Manager()
        eventsManager.getEvents(object : EPAM_Events_Manager.EPAM_Events_Callback {
            override fun onSuccess(events: EPAM_Events_Response?) {
                events?.let {
                    showEvents(events)
                }
            }

            override fun onFail(t: Throwable) {
                showError(t.message)
            }
        })

    }

    fun showEvents(events: EPAM_Events_Response) {
        eventsAdapter = EPAM_Events_Adapter(events)
        mRecyclerView.adapter = eventsAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun showError(message: String?) {
        Toast.makeText(this, "Error: $message", Toast.LENGTH_LONG).show()
    }
}

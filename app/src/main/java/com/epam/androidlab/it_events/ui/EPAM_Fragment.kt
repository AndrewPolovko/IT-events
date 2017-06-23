package com.epam.androidlab.it_events.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.epam.androidlab.it_events.R
import com.epam.androidlab.it_events.data.EPAM_Events_Adapter
import com.epam.androidlab.it_events.data.EPAM_Events_Manager
import com.epam.androidlab.it_events.data.models.EPAM_Events_Response

class EPAM_Fragment : BazeEventsFragment() {
    lateinit var rootView: View
    lateinit var mRecyclerView: RecyclerView
    lateinit var eventsAdapter: EPAM_Events_Adapter
    lateinit var eventsManager: EPAM_Events_Manager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        rootView = inflater.inflate(R.layout.epam_fragment, null)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mRecyclerView = rootView.findViewById(R.id.recycler_view) as RecyclerView
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

    override fun <T> showEvents(events: T) {
        eventsAdapter = EPAM_Events_Adapter(events as EPAM_Events_Response)
        mRecyclerView.adapter = eventsAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(rootView.context)
    }
}
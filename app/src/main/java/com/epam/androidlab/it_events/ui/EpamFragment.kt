package com.epam.androidlab.it_events.ui

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.epam.androidlab.it_events.R
import com.epam.androidlab.it_events.data.EpamEventsAdapter
import com.epam.androidlab.it_events.data.EpamEventsManager
import com.epam.androidlab.it_events.data.models.EpamEventsResponse

class EpamFragment : BazeEventsFragment(), SwipeRefreshLayout.OnRefreshListener {
    lateinit var rootView: View
    lateinit var mRecyclerView: RecyclerView
    lateinit var eventsAdapter: EpamEventsAdapter
    lateinit var eventsManager: EpamEventsManager
    lateinit var mSwipeContainer: SwipeRefreshLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        rootView = inflater.inflate(R.layout.epam_fragment, null)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mRecyclerView = rootView.findViewById(R.id.recycler_view) as RecyclerView
        mSwipeContainer = rootView.findViewById(R.id.swipeContainer) as SwipeRefreshLayout

        mSwipeContainer.setOnRefreshListener(this)
        eventsManager = EpamEventsManager()

        mSwipeContainer.isRefreshing = true
        onRefresh()
    }

    override fun onRefresh() {
        eventsManager.getEvents(object : EpamEventsManager.EpamEventsCallback {
            override fun onSuccess(events: EpamEventsResponse?) {
                events?.let {
                    showEvents(events)
                    stopRefreshingSpinner()
                }
            }

            override fun onFail(t: Throwable) {
                showError(t.message)
                stopRefreshingSpinner()
            }
        })
    }

    override fun <T> showEvents(events: T) {
        eventsAdapter = EpamEventsAdapter(events as EpamEventsResponse)
        mRecyclerView.adapter = eventsAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(rootView.context)
    }

    private fun stopRefreshingSpinner() {
        if (mSwipeContainer.isRefreshing) {
            mSwipeContainer.isRefreshing = false
        }
    }
}
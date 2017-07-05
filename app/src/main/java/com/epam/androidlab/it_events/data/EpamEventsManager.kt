package com.epam.androidlab.it_events.data

import com.epam.androidlab.it_events.EventsApp
import com.epam.androidlab.it_events.data.models.EpamEventsResponse
import com.epam.androidlab.it_events.data.network.EpamApi
import com.epam.androidlab.it_events.util.EpamUtil
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class EpamEventsManager {
    private val mEpamApi: EpamApi = EventsApp.mEpamApi
    fun getEvents(callback: EpamEventsCallback,
                  isUpcoming: Boolean = true,
                  selectedCount: Int = 0) {

        if (!networkIsAvailable(callback)) {
            return
        }
        val observable = mEpamApi.getEvents(isUpcoming, selectedCount)
        getApiResponse(observable, callback)
    }

    fun searchEvents(searchString: String,
                     callback: EpamEventsCallback,
                     isUpcoming: Boolean = true,
                     selectedCount: Int = 0) {

        if (!networkIsAvailable(callback)) {
            return
        }
        val observable = mEpamApi.searchEvents(searchString, isUpcoming, selectedCount)
        getApiResponse(observable, callback)
    }

    fun networkIsAvailable(callback: EpamEventsCallback): Boolean {
        if (EventsApp.isNetworkAvailable()) {
            return true
        } else {
            callback.onFail(Throwable("No network access"))
            return false
        }

    }

    private fun getApiResponse(observable: Observable<EpamEventsResponse>, callback: EpamEventsCallback) {
        observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { events ->
                            EpamUtil.fixImageLinks(events)
                            EpamUtil.addSpacesToEventsTopics(events)
                            callback.onSuccess(events)
                        },
                        { error ->
                            callback.onFail(error)
                        }
                )
    }

    interface EpamEventsCallback {
        fun onSuccess(events: EpamEventsResponse?)
        fun onFail(t: Throwable)
    }
}
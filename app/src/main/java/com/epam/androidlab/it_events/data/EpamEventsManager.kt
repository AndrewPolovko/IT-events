package com.epam.androidlab.it_events.data

import com.epam.androidlab.it_events.App
import com.epam.androidlab.it_events.data.models.EpamEventsResponse
import com.epam.androidlab.it_events.data.network.EpamApi
import com.epam.androidlab.it_events.util.EpamUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EpamEventsManager {
    private val mEpamApi: EpamApi = App.mEpamApi
    fun getEvents(callback: EpamEventsCallback,
                  isUpcoming: Boolean = true,
                  selectedCount: Int = 0) {

        if (App.isNetworkAvailable()) {
            val call = mEpamApi.getEvents(isUpcoming, selectedCount)
            val response = getApiResponse(call, callback)
        } else {
            callback.onFail(Throwable("No network access"))
        }
    }

    fun searchEvents(searchString: String,
                     callback: EpamEventsCallback,
                     isUpcoming: Boolean = true,
                     selectedCount: Int = 0) {

        if (App.isNetworkAvailable()) {
            val call = mEpamApi.searchEvents(searchString, isUpcoming, selectedCount)
            val response = getApiResponse(call, callback)
        } else {
            callback.onFail(Throwable("No network access"))
        }
    }

    private fun getApiResponse(call: Call<EpamEventsResponse>, callback: EpamEventsCallback) {
        call.enqueue(object : Callback<EpamEventsResponse> {
            override fun onFailure(call: Call<EpamEventsResponse>, t: Throwable) {
                callback.onFail(t)
            }

            override fun onResponse(call: Call<EpamEventsResponse>, response: Response<EpamEventsResponse>) {
                if (response.isSuccessful) {
                    val events = response.body()
                    callback.onSuccess(events)
                    if (events != null) {
                        EpamUtil.fixImageLinks(events)
                        EpamUtil.addSpacesToTopics(events)
                    }
                }
            }
        })
    }

    interface EpamEventsCallback {
        fun onSuccess(events: EpamEventsResponse?)
        fun onFail(t: Throwable)
    }
}
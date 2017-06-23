package com.epam.androidlab.it_events.data

import com.epam.androidlab.it_events.MyApp
import com.epam.androidlab.it_events.data.models.EPAM_Events_Response
import com.epam.androidlab.it_events.data.network.EPAM_Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EPAM_Events_Manager(private val api: EPAM_Api = EPAM_Api()) {
    fun getEvents(callback: EPAM_Events_Callback,
                  isUpcoming: Boolean = true,
                  selectedCount: Int = 0) {

        if (MyApp.isNetworkAvailable()) {
            val call = api.getEvents(isUpcoming, selectedCount)
            val response = getApiResponse(call, callback)
        } else {
            callback.onFail(Throwable("No network access"))
        }
    }

    fun searchEvents(searchString: String,
                     callback: EPAM_Events_Callback,
                     isUpcoming: Boolean = true,
                     selectedCount: Int = 0) {

        if (MyApp.isNetworkAvailable()) {
            val call = api.searchEvents(searchString, isUpcoming, selectedCount)
            val response = getApiResponse(call, callback)
        } else {
            callback.onFail(Throwable("No network access"))
        }
    }

    private fun getApiResponse(call: Call<EPAM_Events_Response>, callback: EPAM_Events_Callback) {
        call.enqueue(object : Callback<EPAM_Events_Response> {
            override fun onFailure(call: Call<EPAM_Events_Response>, t: Throwable) {
                callback.onFail(t)
            }

            override fun onResponse(call: Call<EPAM_Events_Response>, response: Response<EPAM_Events_Response>) {
                if (response.isSuccessful) {
                    val events = response.body()
                    callback.onSuccess(events)
                    if (events != null) {
                        fixImageLinks(events)
                        addSpacesToTopics(events)
                    }
                }
            }
        })
    }

    interface EPAM_Events_Callback {
        fun onSuccess(events: EPAM_Events_Response?)
        fun onFail(t: Throwable)
    }

    fun fixImageLinks(Response: EPAM_Events_Response) {
        val pattern = "\\\\".toRegex()
        for (event in Response.events) {
            event.eventImage = event.eventImage.replace(pattern, "")
        }
    }

    fun addSpacesToTopics(Response: EPAM_Events_Response) {
        val pattern = ",".toRegex()
        for (event in Response.events) {
            event.eventImage = event.eventImage.replace(pattern, " , ")
        }
    }
}
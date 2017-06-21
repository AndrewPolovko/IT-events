package com.epam.androidlab.it_events.data

import com.epam.androidlab.it_events.MyApp
import com.epam.androidlab.it_events.data.models.EPAM_Events_Response
import com.epam.androidlab.it_events.data.network.EPAM_Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EPAM_Events_Manager(private val api: EPAM_Api = EPAM_Api()) {
    fun getEvents(callback: EPAM_Events_Callback) {
        if (MyApp.isNetworkAvailable()) {

            val callResponse = api.getEvents()
            val response = callResponse.enqueue(object : Callback<EPAM_Events_Response> {
                override fun onFailure(call: Call<EPAM_Events_Response>, t: Throwable) {
                    callback.onFail(t)
                }

                override fun onResponse(call: Call<EPAM_Events_Response>, response: Response<EPAM_Events_Response>) {
                    if (response.isSuccessful) {
                        val events = response.body()
                        callback.onSuccess(events)
                        if (events != null) {
                            fixImageLinks(events)
                        }
                    }
                }
            })

        } else {
            callback.onFail(Throwable("No network access"))
        }

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
}
package com.epam.androidlab.it_events

import com.epam.androidlab.it_events.models.EPAM_Events_Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EPAM_Events_Manager(private val api: EPAM_Api = EPAM_Api()) {
    fun getEvents(callback: EPAM_Events_Callback) {
        val callResponse = api.getEvents()
        val response = callResponse.enqueue(object : Callback<EPAM_Events_Response?> {
            override fun onFailure(call: Call<EPAM_Events_Response?>, t: Throwable) {
                callback.onFail(t)
            }

            override fun onResponse(call: Call<EPAM_Events_Response?>, response: Response<EPAM_Events_Response?>) {
                if (response.isSuccessful) {
                    callback.onSuccess(response.body())
                }
            }
        })
    }

    interface EPAM_Events_Callback {
        fun onSuccess(events: EPAM_Events_Response?)
        fun onFail(t: Throwable)
    }
}
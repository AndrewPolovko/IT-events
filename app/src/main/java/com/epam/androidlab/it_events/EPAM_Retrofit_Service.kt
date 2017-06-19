package com.epam.androidlab.it_events

import com.epam.androidlab.it_events.models.EPAM_Events_Response
import retrofit2.Call
import retrofit2.http.GET


interface EPAM_Retrofit_Service {
    @GET("events/paginated.json")
    fun getEvents(): Call<EPAM_Events_Response>
}
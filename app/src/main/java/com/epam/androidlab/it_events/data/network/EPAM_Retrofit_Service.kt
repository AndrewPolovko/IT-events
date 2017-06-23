package com.epam.androidlab.it_events.data.network

import com.epam.androidlab.it_events.data.models.EPAM_Events_Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface EPAM_Retrofit_Service {
    @GET("events/paginated.json")
    fun getEvents(
            @Query("is_upcoming") isUpcoming: Boolean,
            @Query("selected_count") selectedCount: Int): Call<EPAM_Events_Response>

    @GET("events/paginated.json")
    fun searchEvents(
            @Query("search") search: String,
            @Query("selected_count") selectedCount: Int,
            @Query("is_upcoming") isUpcoming: Boolean): Call<EPAM_Events_Response>
}
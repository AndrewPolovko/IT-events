package com.epam.androidlab.it_events.data.network

import com.epam.androidlab.it_events.data.models.EpamEventsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface EpamRetrofitService {
    @GET("events/paginated.json")
    fun getEvents(
            @Query("is_upcoming") isUpcoming: Boolean,
            @Query("selected_count") selectedCount: Int): Call<EpamEventsResponse>

    @GET("events/paginated.json")
    fun searchEvents(
            @Query("search") search: String,
            @Query("selected_count") selectedCount: Int,
            @Query("is_upcoming") isUpcoming: Boolean): Call<EpamEventsResponse>
}
package com.epam.androidlab.it_events.data.network

import com.epam.androidlab.it_events.data.models.EpamEventsResponse
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable


interface EpamRetrofitService {
    @GET("events/paginated.json?filters%5BLocation%5D%5B%5D=Belarus")
    fun getEvents(
            @Query("is_upcoming") isUpcoming: Boolean,
            @Query("selected_count") selectedCount: Int): Observable<EpamEventsResponse>

    @GET("events/paginated.json?filters%5BLocation%5D%5B%5D=Belarus")
    fun searchEvents(
            @Query("search") search: String,
            @Query("selected_count") selectedCount: Int,
            @Query("is_upcoming") isUpcoming: Boolean): Observable<EpamEventsResponse>
}
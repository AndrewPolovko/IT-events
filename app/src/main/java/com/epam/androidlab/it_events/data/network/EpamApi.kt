package com.epam.androidlab.it_events.data.network

import com.epam.androidlab.it_events.Extras
import com.epam.androidlab.it_events.data.models.EpamEventsResponse
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import rx.Observable


class EpamApi {
    private val mEpamRetrofitService: EpamRetrofitService

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(Extras.EPAM_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        mEpamRetrofitService = retrofit.create(EpamRetrofitService::class.java)
    }

    fun getEvents(isUpcoming: Boolean,
                  selectedCount: Int): Observable<EpamEventsResponse> {
        return mEpamRetrofitService.getEvents(isUpcoming, selectedCount)
    }

    fun searchEvents(search: String,
                     isUpcoming: Boolean,
                     selectedCount: Int): Observable<EpamEventsResponse> {
        return mEpamRetrofitService.searchEvents(search, selectedCount, isUpcoming)
    }
}
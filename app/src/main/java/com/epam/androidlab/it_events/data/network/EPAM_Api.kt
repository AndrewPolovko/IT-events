package com.epam.androidlab.it_events.data.network

import com.epam.androidlab.it_events.Extras
import com.epam.androidlab.it_events.data.models.EPAM_Events_Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class EPAM_Api {
    private val epamRetrofitService: EPAM_Retrofit_Service

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(Extras.EPAM_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        epamRetrofitService = retrofit.create(EPAM_Retrofit_Service::class.java)
    }

    fun getEvents(): Call<EPAM_Events_Response> {
        return epamRetrofitService.getEvents()
    }
}
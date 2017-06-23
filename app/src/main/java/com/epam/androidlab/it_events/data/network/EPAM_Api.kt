package com.epam.androidlab.it_events.data.network

import com.epam.androidlab.it_events.Extras
import com.epam.androidlab.it_events.data.models.EPAM_Events_Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class EPAM_Api {
    private val mEPAM_Retrofit_Service: EPAM_Retrofit_Service

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(Extras.EPAM_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        mEPAM_Retrofit_Service = retrofit.create(EPAM_Retrofit_Service::class.java)
    }

    fun getEvents(isUpcoming: Boolean,
                  selectedCount: Int): Call<EPAM_Events_Response> {
        return mEPAM_Retrofit_Service.getEvents(isUpcoming, selectedCount)
    }

    fun searchEvents(search: String,
                     isUpcoming: Boolean,
                     selectedCount: Int): Call<EPAM_Events_Response> {
        return mEPAM_Retrofit_Service.searchEvents(search, selectedCount, isUpcoming)
    }
}
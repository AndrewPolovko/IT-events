package com.epam.androidlab.it_events

import com.epam.androidlab.it_events.models.DevByEventsPOJO
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit



class DevBy_data_helper {
    companion object{
        fun getData(): DevByEventsPOJO{

            val BASE_URL = Constants.DEVBY_BASE_URL
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            TODO()
        }
    }
}
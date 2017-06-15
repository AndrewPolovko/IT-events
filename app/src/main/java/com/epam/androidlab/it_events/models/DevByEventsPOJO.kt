package com.epam.androidlab.it_events.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DevByEventsPOJO {

    @SerializedName("events")
    @Expose
    lateinit var events: List<DevByEventPOJO>

}

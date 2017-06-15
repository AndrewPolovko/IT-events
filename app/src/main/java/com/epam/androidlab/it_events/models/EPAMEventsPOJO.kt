package com.epam.androidlab.it_events.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EPAMEventsPOJO {

    @SerializedName("items_count")
    @Expose
    var itemsCount: Int = 0

    @SerializedName("selected_count")
    @Expose
    var selectedCount: Int = 0

    @SerializedName("is_upcoming")
    @Expose
    var isIsUpcoming: Boolean = false

    @SerializedName("EPAMEventPOJOs")
    @Expose
    lateinit var epamEventPOJOs: List<EPAMEventPOJO>

}

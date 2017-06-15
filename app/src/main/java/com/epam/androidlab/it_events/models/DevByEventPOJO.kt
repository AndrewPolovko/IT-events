package com.epam.androidlab.it_events.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DevByEventPOJO {

    @SerializedName("name")
    @Expose
    lateinit var name: String

    @SerializedName("url")
    @Expose
    lateinit var url: String

    @SerializedName("date_and_time")
    @Expose
    lateinit var dateAndTime: String

    @SerializedName("google_calendar")
    @Expose
    lateinit var googleCalendar: String

    @SerializedName("google_calendar_url")
    @Expose
    lateinit var googleCalendarUrl: String

}

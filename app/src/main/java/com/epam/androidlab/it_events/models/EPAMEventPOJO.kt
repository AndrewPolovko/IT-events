package com.epam.androidlab.it_events.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EPAMEventPOJO {

    @SerializedName("block_class")
    @Expose
    lateinit var blockClass: String

    @SerializedName("is_extra_block")
    @Expose
    var isIsExtraBlock: Boolean = false

    @SerializedName("event_image")
    @Expose
    lateinit var eventImage: String

    @SerializedName("event_url")
    @Expose
    lateinit var eventUrl: String

    @SerializedName("date_with_location")
    @Expose
    lateinit var dateWithLocation: String

    @SerializedName("title")
    @Expose
    lateinit var title: String

    @SerializedName("reg_status_class")
    @Expose
    lateinit var regStatusClass: String

    @SerializedName("reg_status")
    @Expose
    lateinit var regStatus: String

    @SerializedName("topics")
    @Expose
    lateinit var topics: String

    @SerializedName("speakers_type")
    @Expose
    lateinit var speakersType: String

    @SerializedName("speakers_photos")
    @Expose
    lateinit var speakersPhotos: String

    @SerializedName("speakers_count")
    @Expose
    var speakersCount: Int = 0

    @SerializedName("participants")
    @Expose
    var participants: Int = 0

}

package com.epam.androidlab.it_events.data.models

import com.squareup.moshi.Json

class EPAM_Events_Response(
        @Json(name = "items_count")
        val itemsCount: Int,
        @Json(name = "selected_count")
        val selectedCount: Int,
        @Json(name = "is_upcoming")
        val isUpcoming: Boolean,
        @Json(name = "rows")
        val events: List<EPAM_Event_Response>
)

class EPAM_Event_Response(
        @Json(name = "block_class")
        val blockClass: String,
        @Json(name = "is_extra_block")
        val isExtraBlock: Boolean,
        @Json(name = "event_image")
        var eventImage: String,
        @Json(name = "event_url")
        val eventUrl: String,
        @Json(name = "date_with_location")
        val dateWithLocation: String,
        @Json(name = "title")
        val title: String,
        @Json(name = "reg_status_class")
        val regStatusClass: String,
        @Json(name = "reg_status")
        val regStatus: String,
        @Json(name = "topics")
        var topics: String,
        @Json(name = "participants")
        val participants: Int,
        @Json(name = "speakers_type")
        val speakersType: String,
        @Json(name = "speakers_photos")
        val speakersPhotos: String,
        @Json(name = "speakers_count")
        val speakersCount: Int
)
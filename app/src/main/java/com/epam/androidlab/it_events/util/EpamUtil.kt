package com.epam.androidlab.it_events.util

import com.epam.androidlab.it_events.data.models.EpamEventsResponse

class EpamUtil {
    companion object {
        fun fixImageLinks(Response: EpamEventsResponse) {
            val pattern = "\\\\".toRegex()
            for (event in Response.events) {
                event.eventImage = event.eventImage.replace(pattern, "")
            }
        }

        fun addSpacesToTopics(Response: EpamEventsResponse) {
            val pattern = ",".toRegex()
            for (event in Response.events) {
                event.topics = event.topics.replace(pattern, ", ")
            }
        }
    }
}
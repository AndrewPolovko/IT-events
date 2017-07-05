package com.epam.androidlab.it_events.util

import com.epam.androidlab.it_events.data.models.EpamEventResponse
import com.epam.androidlab.it_events.data.models.EpamEventsResponse

class EpamUtil {
    companion object {
        fun fixImageLinks(Response: EpamEventsResponse) {
            val pattern = "\\\\".toRegex()
            for (event in Response.events) {
                event.eventImage = event.eventImage.replace(pattern, "")
            }
        }

        fun fixSomthing(Response: EpamEventsResponse) {
            val pattern = "A".toRegex()
            for (event in Response.events) {
                event.topics = event.topics.replace(pattern, "")
            }
        }

        fun addSpacesToEventsTopics(Response: EpamEventsResponse) {
            for (event in Response.events) {
                addSpacesToEventTopics(event)
            }
        }

        fun addSpacesToEventTopics(event: EpamEventResponse) {
            val pattern = ",".toRegex()
            event.topics = event.topics.replace(pattern, ", ")
        }
    }
}
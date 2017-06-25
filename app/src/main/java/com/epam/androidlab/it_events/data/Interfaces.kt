package com.epam.androidlab.it_events.data

interface EventsManager {
    fun getEvents(callback: EventsCallback)
}

interface EventsCallback {
    fun <T> onSuccess(events: T?)
    fun onFail(t: Throwable)
}
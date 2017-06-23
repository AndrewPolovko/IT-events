package com.epam.androidlab.it_events.ui

import android.support.v4.app.Fragment
import android.widget.Toast

abstract class BazeEventsFragment : Fragment() {
    abstract fun <T> showEvents(events: T)
    fun showError(message: String?) {
        Toast.makeText(activity, "Error: $message", Toast.LENGTH_LONG).show()
    }
}
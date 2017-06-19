package com.epam.androidlab.it_events

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.epam.androidlab.it_events.models.EPAM_Events_Response

class EPAM_Events_Adapter(var eventsList: EPAM_Events_Response) : RecyclerView.Adapter<EPAM_Events_Adapter.EPAM_Event_Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EPAM_Event_Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.epam_event_recycler_view_item, parent, false)
        return EPAM_Event_Holder(view)
    }

    inner class EPAM_Event_Holder(var view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.event_title) as TextView
        val dateWithLocation: TextView = view.findViewById(R.id.event_date_with_location) as TextView
        val eventImage: ImageView = view.findViewById(R.id.event_image) as ImageView

        init {
            view.setOnClickListener {
                //TODO
            }
        }
    }

    override fun onBindViewHolder(holder: EPAM_Event_Holder, position: Int) {
        val event = eventsList.events[position]
        holder.title.text = event.title
        holder.dateWithLocation.text = event.dateWithLocation
        Glide.with(holder.view).load(event.eventImage).into(holder.eventImage)
    }

    override fun getItemCount(): Int {
        return eventsList.events.size
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
}
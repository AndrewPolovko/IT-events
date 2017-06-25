package com.epam.androidlab.it_events.data

import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.epam.androidlab.it_events.Extras
import com.epam.androidlab.it_events.R
import com.epam.androidlab.it_events.data.models.EpamEventsResponse


class EpamEventsAdapter(var eventsList: EpamEventsResponse) : RecyclerView.Adapter<EpamEventsAdapter.EpamEventHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpamEventHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.epam_event_recycler_view_item, parent, false)
        return EpamEventHolder(view)
    }

    inner class EpamEventHolder(var view: View) : RecyclerView.ViewHolder(view) {
        val eventImage = view.findViewById(R.id.event_image) as ImageView
        val title = view.findViewById(R.id.event_title) as TextView
        val dateWithLocation = view.findViewById(R.id.event_date_with_location) as TextView
        val topics = view.findViewById(R.id.event_topics) as TextView
        val regStatus = view.findViewById(R.id.reg_status) as TextView
    }

    override fun onBindViewHolder(holder: EpamEventHolder, position: Int) {
        val context = holder.view.context
        val event = eventsList.events[position]
        Glide
                .with(holder.view.context)
                .load(event.eventImage)
                .placeholder(android.R.color.darker_gray)
                .error(R.drawable.ic_error_black_24dp)
                .dontAnimate()
                .into(holder.eventImage)

        holder.title.text = event.title
        holder.dateWithLocation.text = event.dateWithLocation
        holder.topics.text = event.topics
        holder.regStatus.text = event.regStatus

        holder.view.setOnClickListener {
            val builder = CustomTabsIntent.Builder()
            builder.setShowTitle(true)
            builder.setToolbarColor(ResourcesCompat.getColor(context.resources, R.color.colorPrimary, null))
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(context, Uri.parse(Extras.EPAM_BASE_EVENT_URL + event.eventUrl))
        }

    }

    override fun getItemCount(): Int {
        return eventsList.events.size
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
}
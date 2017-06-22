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
import com.epam.androidlab.it_events.data.models.EPAM_Events_Response


class EPAM_Events_Adapter(var eventsList: EPAM_Events_Response) : RecyclerView.Adapter<EPAM_Events_Adapter.EPAM_Event_Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EPAM_Event_Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.epam_event_recycler_view_item, parent, false)
        return EPAM_Event_Holder(view)
    }

    inner class EPAM_Event_Holder(var view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById(R.id.event_title) as TextView
        val dateWithLocation = view.findViewById(R.id.event_date_with_location) as TextView
        val eventImage = view.findViewById(R.id.event_image) as ImageView
    }

    override fun onBindViewHolder(holder: EPAM_Event_Holder, position: Int) {
        val context = holder.view.context
        val event = eventsList.events[position]
        holder.title.text = event.title
        holder.dateWithLocation.text = event.dateWithLocation
        Glide
                .with(holder.view.context)
                .load(event.eventImage)
                .placeholder(android.R.color.darker_gray)
                .error(R.drawable.ic_error_black_24dp)
                .dontAnimate()
                .into(holder.eventImage)

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
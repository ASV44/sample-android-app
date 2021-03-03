package com.sample.app.presentation.feed.viewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sample.app.R
import com.sample.app.presentation.feed.models.FeedItem
import com.squareup.picasso.Picasso

class FeedItemViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private var headerTextView: TextView = view.findViewById(R.id.headerTextView)
    private var descriptionTextView: TextView = view.findViewById(R.id.descriptionTextView)
    private var imageView: ImageView = view.findViewById(R.id.imageView)

    fun bind(item: FeedItem) {
        headerTextView.text = item.header
        descriptionTextView.text = item.description
        item.image?.let {
            Picasso.get().load(it).into(imageView)
        }
    }
}
package com.sample.app.presentation.feed.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.app.R
import com.sample.app.presentation.feed.models.FeedItem
import com.sample.app.presentation.feed.viewHolders.FeedItemViewHolder


class FeedRecyclerViewAdapter(private var dataSet: Array<FeedItem>) : RecyclerView.Adapter<FeedItemViewHolder>() {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedItemViewHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.feed_cell_layout, parent, false)
        // set the view's size, margins, paddings and layout parameters

        return FeedItemViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: FeedItemViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.bind(dataSet[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    fun updateDataSet(dataSet: Array<FeedItem>) {
        this.dataSet = dataSet
        notifyDataSetChanged()
    }
}

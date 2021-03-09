package com.sample.app.presentation.feed.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.app.databinding.FeedCellLayoutBinding
import com.sample.app.presentation.feed.models.FeedItem
import com.sample.app.presentation.feed.viewHolders.FeedItemViewHolder


class FeedRecyclerViewAdapter(private var dataSet: Array<FeedItem>) : RecyclerView.Adapter<FeedItemViewHolder>() {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedItemViewHolder {
        // Inflate view binding for feed cell
        val binding = FeedCellLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
        )

        return FeedItemViewHolder(binding)
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

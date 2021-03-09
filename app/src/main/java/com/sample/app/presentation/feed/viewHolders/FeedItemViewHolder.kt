package com.sample.app.presentation.feed.viewHolders

import androidx.recyclerview.widget.RecyclerView
import com.sample.app.R
import com.sample.app.databinding.FeedCellLayoutBinding
import com.sample.app.presentation.feed.models.FeedItem
import com.squareup.picasso.Picasso

class FeedItemViewHolder(private val binding: FeedCellLayoutBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: FeedItem) {
        binding.headerTextView.text = item.header
        binding.descriptionTextView.text = item.description
        Picasso.get().load(item.image).placeholder(R.drawable.placeholder).fit().into(binding.imageView)
    }
}
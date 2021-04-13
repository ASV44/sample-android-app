package com.sample.app.presentation.feed

import com.sample.app.presentation.feed.models.FeedItem

/**
 * Created by Vdovicenco Alexandr on 04/13/2021.
 */

interface FeedInput {
    fun showProgress()
    fun hideProgress()
    fun updateUI(dataSet: Array<FeedItem>)
}

interface FeedOutput {
    fun getPastLaunches()
}

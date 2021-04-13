package com.sample.app.presentation.feed

import com.sample.app.data.network.APIClient
import com.sample.app.data.network.models.response.Launch
import com.sample.app.presentation.feed.models.FeedItem
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

/**
 * Created by Vdovicenco Alexandr on 04/13/2021.
 */

class FeedPresenter(val view: FeedInput, private val apiService: APIClient): FeedOutput {

    override fun getPastLaunches() {
        view.showProgress()
        // Run network request in coroutine Global Scope (Background Thread)
        GlobalScope.launch {
            kotlin.runCatching {
                // Request data from API
                apiService.getPastLaunches()
            }.onSuccess {
                // Handle success request
                handleAPIData(it)
            }.onFailure {
                // Handle failure and hide progress overlay
                print(it)
                MainScope().launch { view.hideProgress() }
            }
        }
    }

    private fun handleAPIData(data: ArrayList<Launch>) {
        // Map requested data to FeedItem model
        val dataSet = data.map { FeedItem(
            it.missionName,
            it.details,
            it.links.flickr.original.firstOrNull()?.toString())
        }.toTypedArray()

        // Update UI in main scope (Main Thread)
        MainScope().launch {
            view.updateUI(dataSet)
        }
    }
}
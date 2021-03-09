package com.sample.app.presentation.feed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.app.data.network.APICommunication
import com.sample.app.data.network.models.response.Launch
import com.sample.app.databinding.ActivityFeedBinding
import com.sample.app.presentation.extensions.fadeIn
import com.sample.app.presentation.extensions.fadeOut
import com.sample.app.presentation.feed.adapters.FeedRecyclerViewAdapter
import com.sample.app.presentation.feed.models.FeedItem
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class FeedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFeedBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: FeedRecyclerViewAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var apiService: APICommunication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create Recycler view layout manager, for simple lists use Linear layout
        viewManager = LinearLayoutManager(this)

        // Add empty data set to recycler view adapter
        viewAdapter = FeedRecyclerViewAdapter(emptyArray())

        // Get recycler view from view binding and set layout manager and adapter
        recyclerView = binding.feedRecyclerView.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }

        // Create instance of API Service
        apiService = APICommunication()
        // Request data from API
        getPastLaunches()
    }

    private fun getPastLaunches() {
        showProgress()
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
                MainScope().launch { hideProgress() }
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
            hideProgress()
            recyclerView.visibility = View.VISIBLE
            viewAdapter.updateDataSet(dataSet)
        }
    }

    private fun hideProgress() {
        // Access progress overlay reference from view binding
        binding.progressOverlay.root.fadeOut()
    }

    private fun showProgress() {
        binding.progressOverlay.root.fadeIn()
    }
}

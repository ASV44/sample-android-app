package com.sample.app.presentation.feed

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.app.databinding.ActivityFeedBinding
import com.sample.app.presentation.extensions.fadeIn
import com.sample.app.presentation.extensions.fadeOut
import com.sample.app.presentation.feed.adapters.FeedRecyclerViewAdapter
import com.sample.app.presentation.feed.models.FeedItem
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class FeedActivity : FeedInput, AppCompatActivity() {
    private lateinit var binding: ActivityFeedBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: FeedRecyclerViewAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    // Create instance of Feed Presenter and pass all required dependencies -> View, API Service
    private val presenter: FeedOutput by inject { parametersOf(this) }

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

        // Request data from API
        presenter.getPastLaunches()
    }

    override fun hideProgress() {
        // Access progress overlay reference from view binding
        binding.progressOverlay.root.fadeOut()
    }

    override fun updateUI(dataSet: Array<FeedItem>) {
        hideProgress()
        recyclerView.visibility = View.VISIBLE
        viewAdapter.updateDataSet(dataSet)
    }

    override fun showProgress() {
        binding.progressOverlay.root.fadeIn()
    }

    override fun showErrorAlert(message: String) {
        AlertDialog.Builder(this)
                .setTitle("Data Fetching Error")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show()
    }
}

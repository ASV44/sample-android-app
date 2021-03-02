package com.sample.app.feed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.app.R
import com.sample.app.data.APIService
import com.sample.app.data.models.APICommunication
import com.sample.app.data.models.Launch
import com.sample.app.feed.adapters.FeedRecyclerViewAdapter
import com.sample.app.feed.models.FeedItem
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class FeedActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var apiService: APICommunication
    private lateinit var dataSet: Array<FeedItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        // Create Recycler view layout manager, for simple lists use Linear layout
        viewManager = LinearLayoutManager(this)

        // Create sample data set of 30 dummy elements
        dataSet = Array(30) {
            FeedItem(
                "Amazing header",
                "Some description",
                "https://images.unsplash.com/photo-1490730141103-6cac27aaab94?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80"
            )
        }
        viewAdapter = FeedRecyclerViewAdapter(dataSet)

        // Find recycler view in hierarchy of elements and set layout manager and adapter
        recyclerView = findViewById<RecyclerView>(R.id.feed_recycler_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }

        apiService = APICommunication()
        getPastLaunches()
    }

    private fun getPastLaunches() {
        GlobalScope.launch {
            kotlin.runCatching {
                apiService.getPastLaunches()
            }.onSuccess {
                handleAPIData(it)
            }.onFailure {
                print(it)
            }
        }
    }

    private fun handleAPIData(data: ArrayList<Launch>) {
        dataSet = data.map { FeedItem(
            it.missionName,
            it.details ?: "No description",
            "https://cnet1.cbsistatic.com/img/2ZjmzrycBZQD9Dpnt_EnfQ7TTro=/940x0/2020/05/31/5112f3db-5af6-431c-bc0d-a8108ccad2ee/spacex-falcon-9-launch.jpg")
        }.toTypedArray()
        MainScope().launch {
            viewAdapter.notifyDataSetChanged()
        }
    }
}

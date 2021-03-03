package com.sample.app.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.sample.app.R
import com.sample.app.presentation.feed.FeedActivity
import com.sample.app.presentation.tabs.TabsActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val messageText: TextView = findViewById(R.id.messageText)

        recyclerListExampleButton.setOnClickListener {
            startActivity(Intent(this, FeedActivity::class.java))
        }

        tabsExampleButton.setOnClickListener {
            startActivity(Intent(this, TabsActivity::class.java))
        }
    }
}

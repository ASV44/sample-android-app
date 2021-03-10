package com.sample.app.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sample.app.databinding.ActivityMainBinding
import com.sample.app.presentation.feed.FeedActivity
import com.sample.app.presentation.navigation.NavigationActivity
import com.sample.app.presentation.tabs.TabsActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerListExampleButton.setOnClickListener {
            startActivity(Intent(this, FeedActivity::class.java))
        }

        binding.tabsExampleButton.setOnClickListener {
            startActivity(Intent(this, TabsActivity::class.java))
        }

        binding.navigationExampleButton.setOnClickListener {
            startActivity(Intent(this, NavigationActivity::class.java))
        }
    }
}

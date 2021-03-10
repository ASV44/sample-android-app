package com.sample.app.presentation.tabs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sample.app.R
import com.sample.app.databinding.ActivityTabsBinding
import com.sample.app.presentation.tabs.adapters.ViewPagerAdapter

class TabsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTabsBinding
    private val pagerAdapter = ViewPagerAdapter(this.supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))

        binding.viewPager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }
}

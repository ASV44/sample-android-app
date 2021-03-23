package com.sample.app.presentation.navigation

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.sample.app.R
import com.sample.app.databinding.ActivityNavigationBinding

class NavigationActivity: AppCompatActivity() {
    private lateinit var binding: ActivityNavigationBinding
    private val pagerAdapter = BottomBarViewPagerAdapter(this.supportFragmentManager)
    private lateinit var drawerCoordinator: DrawerCoordinator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = pagerAdapter

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            binding.viewPager.currentItem = pagerAdapter.getMenuItemID(item.itemId)
            true
        }

        binding.viewPager.addOnPageChangeListener(object: ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                binding.bottomNavigation.menu.getItem(position).isChecked = true
            }
        })

        initDrawer()
    }

    private fun initDrawer() {
        setSupportActionBar(binding.toolbar)

        val toggle = ActionBarDrawerToggle(
                this,
                binding.drawerLayout,
                binding.toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        )

        drawerCoordinator = DrawerCoordinator(
                binding.drawerLayout,
                binding.navView,
                toggle,
                Navigator(this)
        )
    }
}
package com.sample.app.presentation.navigation

import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.sample.app.R

/**
 * Created by Vdovicenco Alexandr on 03/23/2021.
 */

class DrawerCoordinator(private val drawer: DrawerLayout,
                        private val navigationView: NavigationView,
                        private val toggle: ActionBarDrawerToggle,
                        private val navigator: Navigator) {

    private val menuItemSelectedConsumers: HashMap<Int, (() -> Unit)>

    init {
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected)
        menuItemSelectedConsumers = HashMap()
        addConsumers()
    }

    private fun addConsumers() {
        menuItemSelectedConsumers[R.id.expenses_activity_menu_item] = navigator::openExpensesActivity
    }

    private fun onNavigationItemSelected(item: MenuItem) : Boolean {
        menuItemSelectedConsumers[item.itemId]?.let { it() }
        drawer.closeDrawer(GravityCompat.START)

        return false
    }
}
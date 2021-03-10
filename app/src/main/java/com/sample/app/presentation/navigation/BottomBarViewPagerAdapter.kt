package com.sample.app.presentation.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.sample.app.R
import com.sample.app.presentation.navigation.fragments.ExpensesFragment
import com.sample.app.presentation.navigation.fragments.MapFragment
import com.sample.app.presentation.navigation.fragments.SettingsFragment

/**
 * Created by Vdovicenco Alexandr on 03/10/2021.
 */
class BottomBarViewPagerAdapter(fragmentManager: FragmentManager):
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val tabsID: Array<Int> = arrayOf(
        R.id.expenses_menu_item,
        R.id.map_menu_item,
        R.id.setting_menu_item
    )

    fun getMenuItemID(itemID: Int): Int {
        return tabsID.indexOf(itemID)
    }

    private val pagerFragments: Array<Fragment> = arrayOf(
        ExpensesFragment(),
        MapFragment(),
        SettingsFragment()
    )

    override fun getItem(position: Int): Fragment {
        return pagerFragments[position]
    }

    override fun getCount(): Int {
        return pagerFragments.size
    }
}

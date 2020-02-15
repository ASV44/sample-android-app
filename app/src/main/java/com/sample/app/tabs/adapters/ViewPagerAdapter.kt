package com.sample.app.tabs.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.sample.app.tabs.fragments.CallsFragment
import com.sample.app.tabs.fragments.FriendsFragment
import com.sample.app.tabs.fragments.GroupsFragment


class ViewPagerAdapter(fragmentManager: FragmentManager):
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val tabTitles: Array<String> = arrayOf(
        "Friends",
        "Groups",
        "Calls"
    )

    private val pagerFragments: Array<Fragment> = arrayOf(
        FriendsFragment(),
        GroupsFragment(),
        CallsFragment()
    )

    override fun getItem(position: Int): Fragment {
        return pagerFragments[position]
    }

    override fun getCount(): Int {
        return pagerFragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }
}
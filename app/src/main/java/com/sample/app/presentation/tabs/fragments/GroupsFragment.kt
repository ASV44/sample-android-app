package com.sample.app.presentation.tabs.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sample.app.databinding.FragmentGroupsBinding

class GroupsFragment : Fragment() {
    private lateinit var binding: FragmentGroupsBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentGroupsBinding.inflate(inflater)

        return binding.root
    }

}

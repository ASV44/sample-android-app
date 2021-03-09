package com.sample.app.presentation.tabs.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sample.app.databinding.FragmentCallsBinding

class CallsFragment: Fragment() {
    private lateinit var binding: FragmentCallsBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentCallsBinding.inflate(inflater)

        return binding.root
    }
}
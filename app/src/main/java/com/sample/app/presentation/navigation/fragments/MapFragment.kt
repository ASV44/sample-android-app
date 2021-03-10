package com.sample.app.presentation.navigation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sample.app.databinding.FragmentMapBinding

/**
 * Created by Vdovicenco Alexandr on 03/10/2021.
 */
class MapFragment: Fragment() {
    private lateinit var binding: FragmentMapBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentMapBinding.inflate(inflater)

        return binding.root
    }
}

package com.sample.app.presentation.tabs.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sample.app.R

class CallsFragment: Fragment() {

    private var rootView: View? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_calls, null)
        }

        return rootView
    }
}
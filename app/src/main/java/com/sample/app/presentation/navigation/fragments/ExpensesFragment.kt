package com.sample.app.presentation.navigation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sample.app.databinding.FragmentExpensesBinding

/**
 * Created by Vdovicenco Alexandr on 03/10/2021.
 */
class ExpensesFragment: Fragment() {
    private lateinit var binding: FragmentExpensesBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentExpensesBinding.inflate(inflater)

        return binding.root
    }
}

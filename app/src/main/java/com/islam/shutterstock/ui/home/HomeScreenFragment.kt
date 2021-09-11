package com.islam.shutterstock.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.islam.shutterstock.databinding.FragmentHomeScreenBinding
import com.islam.shutterstock.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreenFragment : BaseFragment<FragmentHomeScreenBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeScreenBinding
        get() = FragmentHomeScreenBinding::inflate

    override fun setupOnViewCreated(view: View) {
        //start logic code here
    }

}
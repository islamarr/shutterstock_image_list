package com.islam.shutterstock.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.islam.shutterstock.databinding.FragmentHomeScreenBinding
import com.islam.shutterstock.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeScreenFragment : BaseFragment<FragmentHomeScreenBinding>() {

    private val viewModel: HomeScreenViewModel by viewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeScreenBinding
        get() = FragmentHomeScreenBinding::inflate

    override fun setupOnViewCreated(view: View) {

        lifecycleScope.launch {
            viewModel.searchResults("")
        }
    }

}
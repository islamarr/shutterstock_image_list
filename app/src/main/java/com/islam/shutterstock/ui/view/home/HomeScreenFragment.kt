package com.islam.shutterstock.ui.view.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.islam.shutterstock.R
import com.islam.shutterstock.data.network.response.ImageDataResponse
import com.islam.shutterstock.databinding.FragmentHomeScreenBinding
import com.islam.shutterstock.ui.adapters.HomeAdapter
import com.islam.shutterstock.ui.adapters.HomeLoadStateAdapter
import com.islam.shutterstock.ui.base.BaseFragment
import com.islam.shutterstock.ui.intent.MainIntent
import com.islam.shutterstock.ui.viewmodel.HomeScreenViewModel
import com.islam.shutterstock.ui.viewstate.MainState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeScreenFragment : BaseFragment<FragmentHomeScreenBinding>(), View.OnClickListener {

    private val viewModel: HomeScreenViewModel by viewModels()
    private lateinit var homeAdapter: HomeAdapter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeScreenBinding
        get() = FragmentHomeScreenBinding::inflate

    override fun setupOnViewCreated(view: View) {
        initRecyclerView()
        startSearch()
        observeViewModel()
        binding?.listLayout?.retryBtn?.setOnClickListener(this)
    }

    private fun startSearch() {
        lifecycleScope.launch {
            viewModel.searchIntent.send(MainIntent.SearchResults)
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is MainState.Idle -> {
                       // showEmptyList(true)
                    }
                    is MainState.Loading -> {
                        binding?.listLayout?.loadingProgressBar?.visibility = View.VISIBLE
                        showEmptyList(false)
                    }
                    is MainState.ImageData -> {
                        renderList(it.imageDataResponse)
                    }
                    is MainState.Error -> {
                        binding?.listLayout?.list?.visibility = View.GONE
                        binding?.listLayout?.emptyList?.visibility = View.GONE
                        binding?.listLayout?.loadingProgressBar?.visibility = View.GONE
                        if (homeAdapter.itemCount == 0) {
                            showEmptyList(true)
                            binding?.listLayout?.emptyListText?.text = it.error
                        }
                    }
                }
            }
        }
    }

    private suspend fun renderList(pagingData: PagingData<ImageDataResponse>) {
        binding?.listLayout?.loadingProgressBar?.visibility = View.GONE
        showEmptyList(false)
        homeAdapter.submitData(pagingData)
    }

    private fun initRecyclerView() {
        binding?.listLayout?.list?.apply {
            homeAdapter = HomeAdapter()
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = homeAdapter
        }

        binding?.listLayout?.list?.adapter = homeAdapter.withLoadStateHeaderAndFooter(
            header = HomeLoadStateAdapter(homeAdapter),
            footer = HomeLoadStateAdapter(homeAdapter)
        )
    }

    private fun showEmptyList(show: Boolean) {
        binding?.listLayout?.emptyList?.isVisible = show
        binding?.listLayout?.list?.isVisible = !show
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.retryBtn -> {
                startSearch()
            }
        }
    }


}
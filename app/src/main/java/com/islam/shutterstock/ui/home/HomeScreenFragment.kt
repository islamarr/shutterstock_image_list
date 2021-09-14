package com.islam.shutterstock.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.islam.shutterstock.databinding.FragmentHomeScreenBinding
import com.islam.shutterstock.ui.BaseFragment
import com.islam.shutterstock.ui.adapters.HomeAdapter
import com.islam.shutterstock.ui.adapters.HomeLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeScreenFragment : BaseFragment<FragmentHomeScreenBinding>() {

    private val viewModel: HomeScreenViewModel by viewModels()
    private lateinit var homeAdapter: HomeAdapter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeScreenBinding
        get() = FragmentHomeScreenBinding::inflate

    override fun setupOnViewCreated(view: View) {

        initRecyclerView()
        startObserver()

        lifecycleScope.launch {
            viewModel.searchResults().collectLatest {
                homeAdapter.submitData(it)
            }
        }
    }

    private fun startObserver() {

        homeAdapter.addLoadStateListener { loadState ->

            val isEmptyList =
                loadState.refresh is LoadState.NotLoading && homeAdapter.itemCount == 0

            when {
                isEmptyList -> {

                    binding?.listLayout?.list?.visibility = View.GONE

                }
                loadState.refresh is LoadState.Loading -> {

                    binding?.listLayout?.list?.visibility = View.VISIBLE
                    binding?.listLayout?.emptyList?.visibility = View.GONE
                    binding?.listLayout?.loadingProgressBar?.visibility = View.VISIBLE

                }
                else -> {

                    binding?.listLayout?.emptyList?.visibility = View.GONE
                    binding?.listLayout?.loadingProgressBar?.visibility = View.GONE

                    val errorState = when {
                        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                        else -> null
                    }

                    errorState?.let {
                        //     binding?.listLayout?.list?.visibility = View.GONE
                        //     binding?.listLayout?.emptyList?.visibility = View.VISIBLE
                        //     binding?.listLayout?.emptyList?.text = getString(R.string.no_internet_connection)
                    }

                }
            }
        }

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


}
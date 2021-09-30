package com.islam.shutterstock.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.islam.shutterstock.R
import com.islam.shutterstock.databinding.FragmentHomeScreenBinding
import com.islam.shutterstock.ui.adapters.HomeAdapter
import com.islam.shutterstock.ui.adapters.HomeLoadStateAdapter
import com.islam.shutterstock.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeScreenFragment : BaseFragment<FragmentHomeScreenBinding>(), View.OnClickListener {

    private val mDisposable = CompositeDisposable()
    private val viewModel: HomeScreenViewModel by viewModels()
    private lateinit var homeAdapter: HomeAdapter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeScreenBinding
        get() = FragmentHomeScreenBinding::inflate

    override fun setupOnViewCreated(view: View) {

        initRecyclerView()
        startSearch()
        startObserver()
        binding?.listLayout?.retryBtn?.setOnClickListener(this)

    }

    private fun startSearch() {
        mDisposable.add(viewModel.searchResults().subscribe {
            homeAdapter.submitData(lifecycle, it)
        })
    }

    private fun startObserver() {
        homeAdapter.addLoadStateListener { loadState ->

            val isEmptyList =
                loadState.refresh is LoadState.NotLoading && homeAdapter.itemCount == 0

            when {
                isEmptyList -> binding?.listLayout?.list?.visibility = View.GONE

                loadState.refresh is LoadState.Loading -> {
                    showEmptyList(false)
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
                        if (homeAdapter.itemCount == 0) {
                            showEmptyList(true)
                            binding?.listLayout?.emptyListText?.text = it.error.message
                        }
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

    override fun onDestroyView() {
        mDisposable.dispose()
        super.onDestroyView()
    }
}
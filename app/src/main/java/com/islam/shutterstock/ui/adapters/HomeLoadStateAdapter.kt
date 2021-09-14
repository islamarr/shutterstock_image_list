package com.islam.shutterstock.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.islam.shutterstock.R
import com.islam.shutterstock.databinding.ListNetworkStateBinding

class HomeLoadStateAdapter(
    private val adapter: HomeAdapter
) : LoadStateAdapter<HomeLoadStateAdapter.NetworkStateItemViewHolder>() {
    override fun onBindViewHolder(holder: NetworkStateItemViewHolder, loadState: LoadState) {
        holder.bindTo(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): NetworkStateItemViewHolder {
        return NetworkStateItemViewHolder(parent) { adapter.retry() }
    }

    class NetworkStateItemViewHolder(
        parent: ViewGroup,
        private val retryCallback: () -> Unit
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_network_state, parent, false)
    ) {
        private val binding = ListNetworkStateBinding.bind(itemView)
        private val progressBar = binding.progressBar
        private val errorMsg = binding.errorMsg
        private val retry = binding.retryButton
            .also {
                it.setOnClickListener { retryCallback() }
            }

        fun bindTo(loadState: LoadState) {
            progressBar.isVisible = loadState is LoadState.Loading
            retry.isVisible = loadState is LoadState.Error
            errorMsg.isVisible = !(loadState as? LoadState.Error)?.error?.message.isNullOrBlank()
            errorMsg.text = (loadState as? LoadState.Error)?.error?.message
        }
    }

}
package com.islam.shutterstock.ui.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.islam.shutterstock.R
import com.islam.shutterstock.data.network.response.ImageDataResponse
import com.islam.shutterstock.databinding.OneItemListBinding

class HomeAdapter : PagingDataAdapter<ImageDataResponse, HomeAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ImageDataResponse>() {
            override fun areItemsTheSame(
                oldItem: ImageDataResponse,
                newItem: ImageDataResponse
            ): Boolean {

                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ImageDataResponse,
                newItem: ImageDataResponse
            ): Boolean {

                return oldItem == newItem
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            OneItemListBinding.bind(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.one_item_list, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val listItems = getItem(position)
        holder.bind(listItems!!)

    }

    inner class ViewHolder(itemView: OneItemListBinding) : RecyclerView.ViewHolder(itemView.root) {
        private val image: ImageView = itemView.image
        private val description: TextView = itemView.description

        fun bind(listItems: ImageDataResponse) {

            loadImage(itemView.context, listItems.assets.preview.url, image)
            description.text = listItems.description

        }
    }

    fun loadImage(context: Context, url: String?, logo: ImageView) {
        Glide.with(context).load(Uri.parse(url))
            .placeholder(R.drawable.placeholder_image)
            .thumbnail(0.1f)
            .circleCrop()
            .into(logo)
    }

}
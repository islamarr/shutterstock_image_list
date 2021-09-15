package com.islam.shutterstock.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.islam.shutterstock.data.network.response.ImageDataResponse

class HomeDiffCallback : DiffUtil.ItemCallback<ImageDataResponse>() {

    override fun areItemsTheSame(oldItem: ImageDataResponse, newItem: ImageDataResponse) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ImageDataResponse, newItem: ImageDataResponse) =
        oldItem == newItem
}
package com.islam.shutterstock.data.network.response

import com.google.gson.annotations.SerializedName

data class ImageDataResponse(
    val assets: Assets = Assets(),
    val description: String, // Fresh summer salad with prawn,strawberry,avocado,lime and olive.Summer salad,healthy eating
    val id: String, // 1973821043
    @SerializedName("media_type")
    val mediaType: String? = null // image
)
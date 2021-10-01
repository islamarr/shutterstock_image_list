package com.islam.shutterstock.data.network.response

import com.squareup.moshi.Json

data class ImageDataResponse(
    val assets: Assets = Assets(),
    val description: String, // Fresh summer salad with prawn,strawberry,avocado,lime and olive.Summer salad,healthy eating
    val id: String, // 1973821043
    @field:Json(name = "media_type")
    val mediaType: String? = null // image
)
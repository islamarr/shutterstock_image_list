package com.islam.shutterstock.data.network.response

import com.google.gson.annotations.SerializedName


data class ImageResponse(
    val data: List<Data>,
    val page: Int, // 1
    @SerializedName("per_page")
    val perPage: Int, // 1
    @SerializedName("search_id")
    val searchId: String, // 841c2b29-99ee-48ae-9b3d-48ff4a2bb843
    @SerializedName("total_count")
    val totalCount: Int // 2852866
)
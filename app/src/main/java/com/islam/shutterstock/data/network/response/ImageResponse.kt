package com.islam.shutterstock.data.network.response

import com.squareup.moshi.Json

data class ImageResponse(
    val data: List<ImageDataResponse>,
    val page: Int, // 1
    @field:Json(name = "per_page")
    val perPage: Int, // 1
    @field:Json(name = "search_id")
    val searchId: String, // 841c2b29-99ee-48ae-9b3d-48ff4a2bb843
    @field:Json(name = "total_count")
    val totalCount: Int // 2852866
)
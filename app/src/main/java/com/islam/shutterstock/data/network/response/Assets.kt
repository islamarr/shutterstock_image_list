package com.islam.shutterstock.data.network.response

import com.squareup.moshi.Json

data class Assets(
    val preview: Preview = Preview(),
    @field:Json(name = "preview_1000")
    val preview1000: Preview1000 = Preview1000(),
)
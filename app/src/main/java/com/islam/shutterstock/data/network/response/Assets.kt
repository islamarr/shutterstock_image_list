package com.islam.shutterstock.data.network.response

import com.google.gson.annotations.SerializedName


data class Assets(
    val preview: Preview = Preview(),
    @SerializedName("preview_1000")
    val preview1000: Preview1000 = Preview1000(),
)
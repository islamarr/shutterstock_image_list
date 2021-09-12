package com.islam.shutterstock.data.network.response

import com.google.gson.annotations.SerializedName

data class Data(
    val aspect: Double, // 1.5005
    val assets: Assets,
    val contributor: Contributor,
    val description: String, // Fresh summer salad with prawn,strawberry,avocado,lime and olive.Summer salad,healthy eating
    @SerializedName("has_model_release")
    val hasModelRelease: Boolean, // false
    val id: String, // 1973821043
    @SerializedName("image_type")
    val imageType: String, // photo
    @SerializedName("media_type")
    val mediaType: String // image
)
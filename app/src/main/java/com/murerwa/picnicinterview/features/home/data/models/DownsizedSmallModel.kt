package com.murerwa.picnicinterview.features.home.data.models

import com.google.gson.annotations.SerializedName

data class DownsizedSmallModel(
    val height: String,
    val mp4: String,
    @SerializedName("mp4_size")
    val mp4Size: String,
    val width: String
)
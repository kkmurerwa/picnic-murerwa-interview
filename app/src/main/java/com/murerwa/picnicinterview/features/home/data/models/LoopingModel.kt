package com.murerwa.picnicinterview.features.home.data.models

import com.google.gson.annotations.SerializedName

data class LoopingModel(
    val mp4: String,
    @SerializedName("mp4_size")
    val mp4Size: String
)
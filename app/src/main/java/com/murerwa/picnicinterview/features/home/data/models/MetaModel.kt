package com.murerwa.picnicinterview.features.home.data.models

import com.google.gson.annotations.SerializedName

data class MetaModel(
    val msg: String,
    @SerializedName("response_id")
    val responseId: String,
    val status: Int
)
package com.murerwa.picnicinterview.features.home.data.models

import com.google.gson.annotations.SerializedName

data class PaginationModel(
    val count: Int,
    val offset: Int,
    @SerializedName("total_count")
    val totalCount: Int
)
package com.murerwa.picnicinterview.features.home.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GifImage(
    val id: String,
    val imageUrls: ImageUrls,
    val rating: String,
    val title: String,
    val type: String,
    val url: String,
    val bitlyUrl: String,
    val username: String
): Parcelable
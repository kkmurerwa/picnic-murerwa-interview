package com.murerwa.picnicinterview.features.home.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageUrls(
    val downsized: String,
    val downsizedSmall: String,
    val looping: String,
    val original: String,
    val originalStill: String,
    val preview: String,
    val previewGif: String
): Parcelable
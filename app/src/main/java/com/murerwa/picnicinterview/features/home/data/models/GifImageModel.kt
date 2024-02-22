package com.murerwa.picnicinterview.features.home.data.models

import com.google.gson.annotations.SerializedName
import com.murerwa.picnicinterview.features.home.domain.entities.GifImage

data class GifImageModel(
    val id: String,
    val images: ImagesModel,
    val rating: String,
    val title: String,
    val type: String,
    val url: String,
    @SerializedName("bitly_url")
    val bitlyUrl: String,
    val username: String
) {
    fun toGifImage() = GifImage(
        id = id,
        imageUrls = images.toImageUrls(),
        rating = rating,
        title = title,
        type = type,
        url = url,
        bitlyUrl = bitlyUrl,
        username = username
    )
}
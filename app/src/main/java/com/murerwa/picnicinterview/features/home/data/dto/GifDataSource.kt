package com.murerwa.picnicinterview.features.home.data.dto

import com.murerwa.picnicinterview.core.network.SafeApiCaller
import com.murerwa.picnicinterview.features.home.domain.entities.GifImage

interface GifDataSource {
    suspend fun getRandomGif(): GifImage
}

const val ERROR_MESSAGE = "We could not get a random gif. Please try again later."

class RemoteGifDataSource(
    private val gifApi: GifApi
): GifDataSource, SafeApiCaller() {
    override suspend fun getRandomGif(): GifImage {
        val response = gifApi.getRandomGif()

        if (response.meta.msg != "OK") {
            throw Exception(ERROR_MESSAGE)
        }

        return response.data.toGifImage()
    }
}
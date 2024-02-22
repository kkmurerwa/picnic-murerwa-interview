package com.murerwa.picnicinterview.features.home.domain.repositories

import com.murerwa.picnicinterview.core.network.NetworkResult
import com.murerwa.picnicinterview.features.home.domain.entities.GifImage

interface RandomGifRepository {
    suspend fun getRandomGif(): NetworkResult<GifImage>
}
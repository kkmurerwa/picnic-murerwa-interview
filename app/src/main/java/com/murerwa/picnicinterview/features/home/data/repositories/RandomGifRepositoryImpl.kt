package com.murerwa.picnicinterview.features.home.data.repositories

import com.murerwa.picnicinterview.core.network.NetworkResult
import com.murerwa.picnicinterview.core.network.SafeApiCaller
import com.murerwa.picnicinterview.features.home.data.dto.RemoteGifDataSource
import com.murerwa.picnicinterview.features.home.domain.entities.GifImage
import com.murerwa.picnicinterview.features.home.domain.repositories.RandomGifRepository

class RandomGifRepositoryImpl(
    private val gifRemoteGifDataSource: RemoteGifDataSource
): RandomGifRepository, SafeApiCaller() {
    override suspend fun getRandomGif(): NetworkResult<GifImage> {
        return safeApiCall { gifRemoteGifDataSource.getRandomGif() }
    }
}
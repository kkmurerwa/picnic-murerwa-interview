package com.murerwa.picnicinterview.features.home.domain.usecases

import com.murerwa.picnicinterview.core.network.NetworkResult
import com.murerwa.picnicinterview.core.network.UIState
import com.murerwa.picnicinterview.features.home.domain.entities.GifImage
import com.murerwa.picnicinterview.features.home.domain.repositories.RandomGifRepository

interface GetRandomGifUseCase {
    suspend operator fun invoke(): UIState<GifImage>
}

class GetRandomGifUseCaseImpl(
    private val randomGifRepository: RandomGifRepository
): GetRandomGifUseCase {
    override suspend operator fun invoke(): UIState<GifImage> {
        return when (val response = randomGifRepository.getRandomGif()) {
            is NetworkResult.Success -> UIState.Success(response.value)
            is NetworkResult.Failure -> UIState.Error(response.errorMessage)
        }
    }
}
package com.murerwa.picnicinterview.features.home.domain.usecases

import androidx.paging.PagingData
import com.murerwa.picnicinterview.features.home.domain.entities.GifImage
import com.murerwa.picnicinterview.features.home.domain.repositories.SearchGifRepository
import kotlinx.coroutines.flow.Flow

interface SearchGifUseCase {
    suspend operator fun invoke(query: String): Flow<PagingData<GifImage>>
}

class SearchGifUseCaseImpl(
    private val searchGifRepository: SearchGifRepository
): SearchGifUseCase {
    override suspend operator fun invoke(query: String): Flow<PagingData<GifImage>> {
        return searchGifRepository.searchGif(query)
    }
}
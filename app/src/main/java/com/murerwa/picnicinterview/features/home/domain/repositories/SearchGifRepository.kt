package com.murerwa.picnicinterview.features.home.domain.repositories

import androidx.paging.PagingData
import com.murerwa.picnicinterview.features.home.domain.entities.GifImage
import kotlinx.coroutines.flow.Flow

interface SearchGifRepository {
    suspend fun searchGif(query: String): Flow<PagingData<GifImage>>
}
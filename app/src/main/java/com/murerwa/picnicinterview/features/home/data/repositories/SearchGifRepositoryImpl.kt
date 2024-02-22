package com.murerwa.picnicinterview.features.home.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.murerwa.picnicinterview.core.network.SafeApiCaller
import com.murerwa.picnicinterview.features.home.data.dto.GifApi
import com.murerwa.picnicinterview.features.home.data.dto.SearchGifsPagingSource
import com.murerwa.picnicinterview.features.home.domain.entities.GifImage
import com.murerwa.picnicinterview.features.home.domain.repositories.SearchGifRepository
import kotlinx.coroutines.flow.Flow

class SearchGifRepositoryImpl(
    private val gifApi: GifApi
): SearchGifRepository, SafeApiCaller() {
    override suspend fun searchGif(query: String): Flow<PagingData<GifImage>> {
        val pager = Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchGifsPagingSource(
                    gifApi = gifApi,
                    searchQuery = query
                )
            }
        )

        return pager.flow
    }
}
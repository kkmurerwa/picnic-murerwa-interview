package com.murerwa.picnicinterview.features.home.data.dto

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.murerwa.picnicinterview.features.home.domain.entities.GifImage

class SearchGifsPagingSource(
    private val gifApi: GifApi,
    private val searchQuery: String
): PagingSource<Int, GifImage>() {
    private var totalNewsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GifImage> {
        val page = params.key ?: 1
        val limit = 20

        return try {
            val response = gifApi.searchGif(
                searchQuery = searchQuery,
                offset = page * limit
            )

            val gifs = response.data.map { it.toGifImage() }.distinctBy { it.title }

            totalNewsCount += response.data.size

            LoadResult.Page(
                data = gifs,
                nextKey = if (totalNewsCount == response.pagination.totalCount) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GifImage>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
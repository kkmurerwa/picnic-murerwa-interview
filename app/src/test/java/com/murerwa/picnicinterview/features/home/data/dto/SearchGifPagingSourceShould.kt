package com.murerwa.picnicinterview.features.home.data.dto

import androidx.paging.PagingSource
import com.murerwa.picnicinterview.fixtures.tSearchGifResponseModel
import com.murerwa.picnicinterview.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchGifPagingSourceShould: BaseUnitTest() {
    private val gifApi = mock<GifApi>()
    private val searchQuery = "funny"

    @Test
    fun returnGifsIfGetGifsSuccess() = runTest {
        whenever(gifApi.searchGif(searchQuery = searchQuery))
            .thenReturn(tSearchGifResponseModel)

        val pagingSource = SearchGifsPagingSource(
            gifApi,
            searchQuery
        )

        val loadParams = PagingSource.LoadParams.Refresh(
            0, 20, true
        )
        val result = pagingSource.load(loadParams)

        assertTrue(result is PagingSource.LoadResult.Page)
        assertEquals(tSearchGifResponseModel.data.map { it.toGifImage() }
            .distinctBy { it.title }, (result as PagingSource.LoadResult.Page).data
        )
        assertEquals(null, result.prevKey)
        assertEquals(1, result.nextKey)
    }

    @Test
    fun returnErrorIfGetGifsFailed() = runTest {
        whenever(gifApi.searchGif(searchQuery = searchQuery))
            .thenThrow(RuntimeException("Failed to get gifs"))

        val pagingSource = SearchGifsPagingSource(
            gifApi,
            searchQuery
        )

        val loadParams = PagingSource.LoadParams.Refresh(
            0, 20, true
        )
        val result = pagingSource.load(loadParams)

        assertTrue(result is PagingSource.LoadResult.Error)
    }
}
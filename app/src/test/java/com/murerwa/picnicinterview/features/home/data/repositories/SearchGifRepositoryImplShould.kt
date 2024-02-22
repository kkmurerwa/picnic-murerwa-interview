package com.murerwa.picnicinterview.features.home.data.repositories

import androidx.lifecycle.asLiveData
import androidx.paging.PagingData
import com.murerwa.picnicinterview.features.home.data.dto.GifApi
import com.murerwa.picnicinterview.features.home.data.models.SearchGifResponseModel
import com.murerwa.picnicinterview.features.home.domain.entities.GifImage
import com.murerwa.picnicinterview.utils.BaseUnitTest
import com.murerwa.picnicinterview.utils.getValueForTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchGifRepositoryImplShould: BaseUnitTest() {
    private val gifApi = mock<GifApi>()
    private val mockSearchResponseModel = mock<SearchGifResponseModel>()

    @Test
    fun returnPagingDataFlowIfSearchGifSuccess() = runTest {
        whenever(gifApi.searchGif(searchQuery = "test")).thenReturn(mockSearchResponseModel)

        val repository = SearchGifRepositoryImpl(gifApi)

        val actual = repository.searchGif("test")

        val actualValue = actual.asLiveData().getValueForTest()
        assertTrue(actualValue is PagingData<GifImage>)
    }
}
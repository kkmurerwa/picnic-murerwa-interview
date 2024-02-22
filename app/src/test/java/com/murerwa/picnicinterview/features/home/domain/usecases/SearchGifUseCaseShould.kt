package com.murerwa.picnicinterview.features.home.domain.usecases

import androidx.lifecycle.asLiveData
import androidx.paging.PagingData
import com.murerwa.picnicinterview.features.home.domain.entities.GifImage
import com.murerwa.picnicinterview.features.home.domain.repositories.SearchGifRepository
import com.murerwa.picnicinterview.utils.BaseUnitTest
import com.murerwa.picnicinterview.utils.getValueForTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchGifUseCaseShould: BaseUnitTest() {
    private val searchGifRepository =  mock<SearchGifRepository>()
    private val useCase = SearchGifUseCaseImpl(searchGifRepository)

    @Test
    fun invokeSearchGifRepositorySearchGif() = runTest {
        val query = "funny"
        useCase.invoke(query)
        verify(searchGifRepository, times(1)).searchGif(query)
    }

    @Test
    fun returnFlowOfPagingDataGifImage() = runTest {
        val query = "funny"
        val expected = mock<Flow<PagingData<GifImage>>>()

        whenever(searchGifRepository.searchGif(query))
            .thenReturn(expected)
        val actual = useCase.invoke(query)

        val actualValue = actual.asLiveData().getValueForTest()
        val expectedValue = expected.asLiveData().getValueForTest()
        assertEquals(expectedValue, actualValue)
    }
}
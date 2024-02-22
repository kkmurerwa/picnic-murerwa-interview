package com.murerwa.picnicinterview.features.home.data.repositories

import com.murerwa.picnicinterview.core.network.NetworkResult
import com.murerwa.picnicinterview.features.home.data.dto.RemoteGifDataSource
import com.murerwa.picnicinterview.features.home.domain.entities.GifImage
import com.murerwa.picnicinterview.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RandomGifRepositoryImplShould: BaseUnitTest() {
    private val remoteGifDataSource = mock<RemoteGifDataSource>()
    private val mockGifImage = mock<GifImage>()

    @Test
    fun invokeRemoteGifDataSourceGetRandomGif() = runTest {
        val repository = RandomGifRepositoryImpl(remoteGifDataSource)

        repository.getRandomGif()

        verify(remoteGifDataSource, times(1)).getRandomGif()
    }

    @Test
    fun getGifFromRepositoryAsNetworkResultInstance() = runTest {
        whenever(remoteGifDataSource.getRandomGif()).thenReturn(mockGifImage)

        val repository = RandomGifRepositoryImpl(remoteGifDataSource)
        val actual = repository.getRandomGif()

        assertEquals(NetworkResult.Success(mockGifImage), actual)
    }

    @Test
    fun getNetworkResultFailureFromRepositoryIfCallFails() = runTest {
        val runtimeExceptionMessage = "We could not get a random gif. Please try again later."
        whenever(remoteGifDataSource.getRandomGif())
            .thenThrow(RuntimeException(runtimeExceptionMessage))

        val repository = RandomGifRepositoryImpl(remoteGifDataSource)
        val actual = repository.getRandomGif()

        assertEquals(NetworkResult.Failure(runtimeExceptionMessage), actual)
    }
}
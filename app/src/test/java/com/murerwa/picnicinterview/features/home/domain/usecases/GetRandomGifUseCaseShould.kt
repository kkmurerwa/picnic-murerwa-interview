package com.murerwa.picnicinterview.features.home.domain.usecases

import com.murerwa.picnicinterview.core.network.NetworkResult
import com.murerwa.picnicinterview.core.network.UIState
import com.murerwa.picnicinterview.features.home.domain.repositories.RandomGifRepository
import com.murerwa.picnicinterview.fixtures.tGifImageModel
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
class GetRandomGifUseCaseShould: BaseUnitTest() {
    private val randomGifRepository = mock<RandomGifRepository>()
    private val useCase = GetRandomGifUseCaseImpl(randomGifRepository)

    @Test
    fun invokeRandomGifRepositoryGetRandomGif() = runTest {
        val expected = tGifImageModel.toGifImage()
        whenever(randomGifRepository.getRandomGif())
            .thenReturn(NetworkResult.Success(expected))

        useCase.invoke()
        verify(randomGifRepository, times(1)).getRandomGif()
    }

    @Test
    fun returnUIStateSuccessIfGetRandomGifSuccess() = runTest {
        val expected = tGifImageModel.toGifImage()
        whenever(randomGifRepository.getRandomGif())
            .thenReturn(NetworkResult.Success(expected))

        val actual = useCase.invoke()

        assert(actual is UIState.Success)
        assertEquals(expected, (actual as UIState.Success).value)
    }

    @Test
    fun returnUIStateErrorIfGetRandomGifFailed() = runTest {
        val expected = "Error"
        whenever(randomGifRepository.getRandomGif())
            .thenReturn(NetworkResult.Failure(expected))

        val actual = useCase.invoke()

        assert(actual is UIState.Error)
        assertEquals(expected, (actual as UIState.Error).errorMessage)
    }
}
package com.murerwa.picnicinterview.features.home.data.dto

import com.murerwa.picnicinterview.fixtures.tGifImageResponseModel
import com.murerwa.picnicinterview.fixtures.tGifImageResponseModelFailed
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
class GifDataSourceShould: BaseUnitTest() {
    private val gifApi = mock<GifApi>()
    private val gifDataSource = RemoteGifDataSource(gifApi)

    @Test
    fun invokeGifApiGetRandomGif() = runTest {
        whenever(gifApi.getRandomGif()).thenReturn(tGifImageResponseModel)

        gifDataSource.getRandomGif()

        verify(gifApi, times(1)).getRandomGif()
    }

    @Test
    fun returnGifImageWhenGetRandomGifCalled() = runTest {
        whenever(gifApi.getRandomGif()).thenReturn(tGifImageResponseModel)

        val actual = gifDataSource.getRandomGif()

        assertEquals(tGifImageResponseModel.data.toGifImage(), actual)
    }

    @Test
    fun throwExceptionWithExpectedMessageIfGetRandomResponseStatusIsNotOk() = runTest {
        whenever(gifApi.getRandomGif()).thenReturn(tGifImageResponseModelFailed)

        try {
            gifDataSource.getRandomGif()
        } catch (exception: Exception) {
            assertEquals(ERROR_MESSAGE, exception.message)
        }
    }
}
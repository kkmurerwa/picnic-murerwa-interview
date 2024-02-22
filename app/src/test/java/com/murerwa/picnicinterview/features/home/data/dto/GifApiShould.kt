package com.murerwa.picnicinterview.features.home.data.dto

import com.google.gson.Gson
import com.murerwa.picnicinterview.fixtures.tGifImageResponseModel
import com.murerwa.picnicinterview.fixtures.tSearchGifResponseModel
import com.murerwa.picnicinterview.utils.BaseUnitTest
import com.murerwa.picnicinterview.utils.RetrofitHelper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.net.HttpURLConnection

@OptIn(ExperimentalCoroutinesApi::class)
class GifApiShould: BaseUnitTest() {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var gifApi: GifApi

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        gifApi = RetrofitHelper.testGifApi(mockWebServer.url("/").toString())
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun returnGifImageResponseModelIfGetRandomGifIsSuccess() = runTest {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(Gson().toJson(tGifImageResponseModel))
        mockWebServer.enqueue(response)

        val actual = gifApi.getRandomGif()

        assertEquals(tGifImageResponseModel, actual)
    }

    @Test
    fun returnExceptionIfGetRandomGifFailed() = runTest {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_INTERNAL_ERROR)
        mockWebServer.enqueue(response)

        try {
            gifApi.getRandomGif()
        } catch (e: Exception) {
            assertEquals("HTTP 500 Server Error", e.message)
        }
    }

    @Test
    fun returnListOfGifsIfSearchGifsIsSuccess() = runTest {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(Gson().toJson(tSearchGifResponseModel))
        mockWebServer.enqueue(response)

        val actual = gifApi.searchGif(searchQuery = "funny")

        assertEquals(tSearchGifResponseModel, actual)
    }

    @Test
    fun returnExceptionIfSearchGifsFailed() = runTest {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_INTERNAL_ERROR)
        mockWebServer.enqueue(response)

        try {
            gifApi.searchGif(searchQuery = "funny")
        } catch (e: Exception) {
            assertEquals("HTTP 500 Server Error", e.message)
        }
    }
}
package com.murerwa.picnicinterview.features.home.presentation.home

import android.content.Context
import androidx.lifecycle.asLiveData
import androidx.paging.PagingData
import androidx.test.platform.app.InstrumentationRegistry
import coil.Coil
import coil.EventListener
import coil.ImageLoader
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.murerwa.picnicinterview.core.network.UIState
import com.murerwa.picnicinterview.features.home.domain.entities.GifImage
import com.murerwa.picnicinterview.features.home.domain.usecases.GetRandomGifUseCase
import com.murerwa.picnicinterview.features.home.domain.usecases.GifUseCases
import com.murerwa.picnicinterview.features.home.domain.usecases.SearchGifUseCase
import com.murerwa.picnicinterview.fixtures.tGifImageModel
import com.murerwa.picnicinterview.fixtures.tGifImageModel2
import com.murerwa.picnicinterview.utils.BaseUITest
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class HomeScreenViewModelShould: BaseUITest() {
    private val gifUseCases = GifUseCases(
        searchGifUseCase = mock<SearchGifUseCase>(),
        getRandomGifUseCase = mock<GetRandomGifUseCase>()
    )
    private val viewModel = HomeScreenViewModel(gifUseCases)

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun onEventCalledWithGetRandomGifInvokesTheRandomGifUseCase() = runTest {
        viewModel.onEvent(HomeEvent.GetRandomGif)

        composeAndroidTestRule.waitForIdle()

        verify(gifUseCases.getRandomGifUseCase, times(1)).invoke()
    }

    @Test
    fun onEventCalledWithGetRandomGifUpdatesTheRandomGifState() = runTest {
        val expected = tGifImageModel.toGifImage()

        whenever(gifUseCases.getRandomGifUseCase.invoke())
            .thenReturn(UIState.Success(expected))

        viewModel.onEvent(HomeEvent.GetRandomGif)

        composeAndroidTestRule.waitForIdle()

        assertEquals(viewModel.state.value.randomGif, expected)
    }

    @Test
    fun onEventCalledWithGetRandomGifUpdatesTheErrorGifStateIfCallFails() = runTest {
        val expected = "Error"

        whenever(gifUseCases.getRandomGifUseCase.invoke())
            .thenReturn(UIState.Error(expected))

        viewModel.onEvent(HomeEvent.GetRandomGif)

        composeAndroidTestRule.waitForIdle()

        assertEquals(viewModel.state.value.randomGif, null)
        assertEquals(viewModel.state.value.loadingError, expected)
    }

    @Test
    fun onEventCalledWithUpdateSearchQueryUpdatesSearchQuery() = runTest {
        val query = "funny"
        viewModel.onEvent(HomeEvent.UpdateSearchQuery(query))

        composeAndroidTestRule.waitForIdle()

        assertEquals(viewModel.state.value.searchQuery, query)
    }

    @Test
    fun onEventCalledWithSearchGifsInvokesTheSearchGifUseCase() = runTest {
        val query = "funny"
        val mockResponse = mock<Flow<PagingData<GifImage>>>()
        whenever(gifUseCases.searchGifUseCase.invoke(any()))
            .thenReturn(mockResponse)

        viewModel.onEvent(HomeEvent.UpdateSearchQuery(query))

        composeAndroidTestRule.waitForIdle()

        assertEquals(viewModel.state.value.searchQuery, query)

        viewModel.onEvent(HomeEvent.SearchGifs)

        composeAndroidTestRule.waitUntil {
            viewModel.state.value.gifs != null
        }

        verify(gifUseCases.searchGifUseCase, times(1)).invoke(query)
    }

    @Test
    fun onEventCalledWithSearchGifsUpdatesTheGifsState() = runTest {
        val query = "funny"
        val mockResponse: Flow<PagingData<GifImage>> = mock<Flow<PagingData<GifImage>>>()
        whenever(gifUseCases.searchGifUseCase.invoke(any()))
            .thenReturn(mockResponse)

        viewModel.onEvent(HomeEvent.UpdateSearchQuery(query))

        composeAndroidTestRule.waitForIdle()

        assertEquals(viewModel.state.value.searchQuery, query)

        viewModel.onEvent(HomeEvent.SearchGifs)

        composeAndroidTestRule.waitUntil {
            viewModel.state.value.gifs != null
        }

        val actual = viewModel.state.value.gifs?.asLiveData()?.value
        val expected = mockResponse.asLiveData().value

        assertEquals(expected, actual)
    }

    @Test
    fun onEventCalledWithToggleSearchViewUpdatesTheIsInSearchViewState() = runTest {
        assertEquals(viewModel.state.value.isInSearchView, false)

        viewModel.onEvent(HomeEvent.ToggleSearchView(true))

        composeAndroidTestRule.waitForIdle()

        assertEquals(viewModel.state.value.isInSearchView, true)
    }

    @Test
    fun onEventCalledWithStartRandomGifRefreshTimerInvokesTheStartRandomGifRefreshTimer() = runTest {
        val expected = tGifImageModel.toGifImage()
        whenever(gifUseCases.getRandomGifUseCase.invoke())
            .thenReturn(UIState.Success(expected))

        viewModel.resetGifRefreshTime(2)
        viewModel.onEvent(HomeEvent.StartRandomGifRefreshTimer)

        composeAndroidTestRule.waitUntil(10000) {
            viewModel.state.value.randomGif != null
        }

        verify(gifUseCases.getRandomGifUseCase, times(1)).invoke()
        assertEquals(viewModel.state.value.randomGif, expected)
    }
}
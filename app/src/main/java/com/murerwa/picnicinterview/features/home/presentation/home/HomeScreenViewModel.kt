package com.murerwa.picnicinterview.features.home.presentation.home

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.murerwa.picnicinterview.core.network.UIState
import com.murerwa.picnicinterview.features.home.domain.usecases.GifUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val gifUseCases: GifUseCases
) : ViewModel() {
    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> get() = _state

    private var counterTime = 0
    private var refreshTime = 10
    private val timerHandler: Handler = Handler(Looper.getMainLooper())

    private val debounceTime = 500L

    private var searchRequestedTime = 0L

    /**
     * This function is used to handle events from the UI.
     */
    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.GetRandomGif -> {
                getRandomGif()
            }
            is HomeEvent.ToggleSearchView -> {
                _state.value = state.value.copy(isInSearchView = event.isInSearchView)
                if (event.isInSearchView) {
                    // Pause the random gif refresh
                    timerHandler.removeCallbacks(startRandomGifRefreshCountDownTimer)
                } else {
                    // Resume the random gif refresh
                    timerHandler.post(startRandomGifRefreshCountDownTimer)
                }
            }
            is HomeEvent.UpdateSearchQuery -> {
                _state.value = state.value.copy(searchQuery = event.searchQuery)
                searchRequestedTime = System.currentTimeMillis()

                if (event.searchQuery.isEmpty()) {
                    _state.value = state.value.copy(gifs = null)
                }
            }
            is HomeEvent.SearchGifs -> {
                if (System.currentTimeMillis() - searchRequestedTime > debounceTime) {
                    searchGif()
                } else {
                    timerHandler.removeCallbacks(debounceSearch)
                    timerHandler.postDelayed(debounceSearch, debounceTime)
                }
            }
            is HomeEvent.StartRandomGifRefreshTimer -> {
                startRandomGifRefreshTimer()
            }
        }
    }

    private fun getRandomGif() = viewModelScope.launch(Dispatchers.IO) {
        when (val gif = gifUseCases.getRandomGifUseCase()) {
            is UIState.Loading -> {
                _state.value = state.value.copy(randomGif = null)
            }
            is UIState.Success -> {
                _state.value = state.value.copy(randomGif = gif.value)
            }
            is UIState.Error -> {
                _state.value = state.value.copy(randomGif = null)
                _state.value = state.value.copy(loadingError = gif.errorMessage ?: "An error occurred")
            }
        }
    }

    private fun startRandomGifRefreshTimer() {
        timerHandler.post(startRandomGifRefreshCountDownTimer)
    }

    private fun searchGif() = viewModelScope.launch(Dispatchers.IO) {
        val gifs = gifUseCases.searchGifUseCase(
            query = state.value.searchQuery
        ).cachedIn(viewModelScope)

        _state.value = _state.value.copy(gifs = gifs)
    }

    private val startRandomGifRefreshCountDownTimer: Runnable = object : Runnable {
        override fun run() {
            counterTime += 1

            if (counterTime <= refreshTime) {
                timerHandler.postDelayed(this, 1000)
                Log.d("COUNTER_TAG", "run: $counterTime")
            } else {
                timerHandler.removeCallbacks(this)
                counterTime = 0
                getRandomGif()
            }
        }
    }

    private val debounceSearch: Runnable = Runnable {
        searchGif()
    }

    /**
     * This function is used to reset the refresh time for the random gif during tests as tests time
     * out if a request takes more than 10 seconds
     */
    fun resetGifRefreshTime(seconds: Int) {
        refreshTime = seconds
    }
}
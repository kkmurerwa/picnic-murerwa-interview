package com.murerwa.picnicinterview.features.home.presentation.home

sealed class HomeEvent {
    data object GetRandomGif: HomeEvent()
    data class ToggleSearchView(val isInSearchView: Boolean): HomeEvent()
    data class UpdateSearchQuery(val searchQuery: String): HomeEvent()
    data object SearchGifs: HomeEvent()
    data object StartRandomGifRefreshTimer: HomeEvent()
}
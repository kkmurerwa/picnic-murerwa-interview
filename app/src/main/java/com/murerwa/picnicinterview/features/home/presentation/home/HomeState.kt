package com.murerwa.picnicinterview.features.home.presentation.home

import androidx.paging.PagingData
import com.murerwa.picnicinterview.features.home.domain.entities.GifImage
import kotlinx.coroutines.flow.Flow

data class HomeState(
    val randomGif: GifImage? = null,
    val searchQuery: String = "",
    val gifs: Flow<PagingData<GifImage>>? = null,
    val isInSearchView: Boolean = false,
    val loadingError: String = ""
)
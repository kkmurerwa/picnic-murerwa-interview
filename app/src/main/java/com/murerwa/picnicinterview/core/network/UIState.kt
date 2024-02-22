package com.murerwa.picnicinterview.core.network

sealed class UIState<out T> {
    data class Success<out T>(val value: T) : UIState<T>()
    data class Error(val errorMessage: String?) : UIState<Nothing>()
    data object Loading : UIState<Nothing>()
}
package com.murerwa.picnicinterview.core.network

/**
 * A sealed class which holds a network result with a value of type T. The result can be a [Success]
 * or a [Failure].
 */
sealed class NetworkResult<out T> {
    /**
     * A generic class which holds a the Success result of a network call of type T.
     */
    data class Success<out T>(val value: T): NetworkResult<T>()

    /**
     * A generic class which holds a network failure and its properties.
     */
    data class Failure(
        val errorMessage: String
    ): NetworkResult<Nothing>()
}

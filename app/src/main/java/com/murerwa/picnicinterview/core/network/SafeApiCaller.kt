package com.murerwa.picnicinterview.core.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

/**
 * This class class acts as a wrapper to that wraps around any api calls made in the app. It has a
 * function safeApiCall that is used to make a network call in a safe way. It ensures that the
 * network call is made in an IO thread and handles any failure cases to prevent the app from
 * crashing due to a failed API call.
 */
open class SafeApiCaller {
    /**
     * This function makes api calls safer by wrapping the network call in a try-catch block and
     * handling any exceptions that may occur during the network call.
     *
     * @param apiCall the network call to be made. This is a suspend function that returns the result of
     * the network call.
     *
     * @return the result of a network call as a [NetworkResult] object that can be used to
     * handle the success and failure cases of the API call in the app.
     */
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): NetworkResult<T> {
        return withContext(Dispatchers.IO) {
            try {
                NetworkResult.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        NetworkResult.Failure(
                            errorMessage = throwable.message()
                        )
                    }
                    else -> {
                        NetworkResult.Failure(
                            errorMessage = throwable.message ?: "We encountered a network error. Please try again later."
                        )
                    }
                }
            }
        }
    }
}
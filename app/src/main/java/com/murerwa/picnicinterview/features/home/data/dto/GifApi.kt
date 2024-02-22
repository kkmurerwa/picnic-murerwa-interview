package com.murerwa.picnicinterview.features.home.data.dto

import com.murerwa.picnicinterview.BuildConfig
import com.murerwa.picnicinterview.features.home.data.models.GifImageResponseModel
import com.murerwa.picnicinterview.features.home.data.models.SearchGifResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface GifApi {
    @GET("random")
    suspend fun getRandomGif(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): GifImageResponseModel

    @GET("search")
    suspend fun searchGif(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("q") searchQuery: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): SearchGifResponseModel
}
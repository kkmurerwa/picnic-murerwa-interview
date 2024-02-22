package com.murerwa.picnicinterview.utils

import com.murerwa.picnicinterview.features.home.data.dto.GifApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    /**
     * as dependency injection is not used in this project for brevity,
     * static object initialization is done for demo purpose.
     *
     * @param url: Base url for apis
     * @return [GifApi]: Instance of the GIF api retrofit interface
     **/
    fun testGifApi(url: String): GifApi {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GifApi::class.java)
    }
}
package com.murerwa.picnicinterview.features.home.data.di

import com.murerwa.picnicinterview.BuildConfig
import com.murerwa.picnicinterview.core.network.Urls
import com.murerwa.picnicinterview.features.home.data.dto.RemoteGifDataSource
import com.murerwa.picnicinterview.features.home.data.dto.GifApi
import com.murerwa.picnicinterview.features.home.data.repositories.RandomGifRepositoryImpl
import com.murerwa.picnicinterview.features.home.data.repositories.SearchGifRepositoryImpl
import com.murerwa.picnicinterview.features.home.domain.repositories.RandomGifRepository
import com.murerwa.picnicinterview.features.home.domain.repositories.SearchGifRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun providesLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = when (BuildConfig.BUILD_TYPE) {
            "debug" -> HttpLoggingInterceptor.Level.BODY
            else -> HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun providesRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(Urls.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun providesApiClient(retrofit: Retrofit): GifApi =
        retrofit.create(GifApi::class.java)

    @Provides
    @Singleton
    fun providesRemoteGifDataSource(
        gifApi: GifApi
    ): RemoteGifDataSource = RemoteGifDataSource(gifApi)

    @Provides
    @Singleton
    fun providesRandomGifRepository(
        remoteDataSource: RemoteGifDataSource
    ): RandomGifRepository = RandomGifRepositoryImpl(remoteDataSource)

    @Provides
    @Singleton
    fun providesSearchGifRepository(
        gifApi: GifApi
    ): SearchGifRepository = SearchGifRepositoryImpl(gifApi)
}
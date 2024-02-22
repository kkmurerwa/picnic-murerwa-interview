package com.murerwa.picnicinterview.features.home.domain.di

import com.murerwa.picnicinterview.features.home.domain.repositories.RandomGifRepository
import com.murerwa.picnicinterview.features.home.domain.repositories.SearchGifRepository
import com.murerwa.picnicinterview.features.home.domain.usecases.GetRandomGifUseCase
import com.murerwa.picnicinterview.features.home.domain.usecases.GetRandomGifUseCaseImpl
import com.murerwa.picnicinterview.features.home.domain.usecases.GifUseCases
import com.murerwa.picnicinterview.features.home.domain.usecases.SearchGifUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    @Singleton
    fun providesGifUseCases(
        searchGifRepository: SearchGifRepository,
        randomGifRepository: RandomGifRepository
    ): GifUseCases = GifUseCases(
        searchGifUseCase = SearchGifUseCaseImpl(searchGifRepository),
        getRandomGifUseCase = GetRandomGifUseCaseImpl(randomGifRepository)
    )
}
package com.skipissue.mobilebanking.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.skipissue.mobilebanking.data.repository.CardsRepository
import com.skipissue.mobilebanking.data.repository.CardsRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface CardsRepositorModule {
    @Binds
    fun bind(cardsRepositoryImpl: CardsRepositoryImpl):CardsRepository
}
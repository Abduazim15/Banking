package com.skipissue.mobilebanking.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.skipissue.mobilebanking.data.datasource.CardsDataSourse
import com.skipissue.mobilebanking.data.datasource.CardsDataSourseImpl

@Module
@InstallIn(SingletonComponent::class)
interface CardsDataSourceModule {
    @Binds
    fun bind(cardsDataSourceImpl: CardsDataSourseImpl):CardsDataSourse
}
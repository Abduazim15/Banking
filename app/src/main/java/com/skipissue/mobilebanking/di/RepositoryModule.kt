package com.skipissue.mobilebanking.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.skipissue.mobilebanking.data.repository.AuthRepository
import com.skipissue.mobilebanking.data.repository.AuthRepositoryImpl
import com.skipissue.mobilebanking.data.repository.DatabaseRepository
import com.skipissue.mobilebanking.data.repository.DatabaseRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository
    @Binds
    fun bindDataBaseRepo(databaseRepositoryImpl: DatabaseRepositoryImpl) : DatabaseRepository
}
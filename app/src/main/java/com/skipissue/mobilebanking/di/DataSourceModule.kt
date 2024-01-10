package com.skipissue.mobilebanking.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.skipissue.mobilebanking.data.datasource.AuthDataSource
import com.skipissue.mobilebanking.data.datasource.AuthDataSourceImpl
import com.skipissue.mobilebanking.data.datasource.DataBaseDataSource
import com.skipissue.mobilebanking.data.datasource.DataBaseDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
interface DataBaseModule {
    @Binds
    fun bindAuthDataSource(authDataSourceImpl: AuthDataSourceImpl): AuthDataSource
    @Binds
    fun bindDataBase(dataBaseDataSourceImpl: DataBaseDataSourceImpl) : DataBaseDataSource
}
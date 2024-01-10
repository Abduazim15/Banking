package uz.gita.lesson40.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.lesson40.data.datasource.AuthDataSource
import uz.gita.lesson40.data.datasource.AuthDataSourceImpl
import uz.gita.lesson40.data.datasource.DataBaseDataSource
import uz.gita.lesson40.data.datasource.DataBaseDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
interface DataBaseModule {
    @Binds
    fun bindAuthDataSource(authDataSourceImpl: AuthDataSourceImpl): AuthDataSource
    @Binds
    fun bindDataBase(dataBaseDataSourceImpl: DataBaseDataSourceImpl) : DataBaseDataSource
}
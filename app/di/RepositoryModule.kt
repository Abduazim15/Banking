package uz.gita.lesson40.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.lesson40.data.datasource.DataBaseDataSource
import uz.gita.lesson40.data.datasource.DataBaseDataSourceImpl
import uz.gita.lesson40.data.repository.AuthRepository
import uz.gita.lesson40.data.repository.AuthRepositoryImpl
import uz.gita.lesson40.data.repository.DatabaseRepository
import uz.gita.lesson40.data.repository.DatabaseRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository
    @Binds
    fun bindDataBaseRepo(databaseRepositoryImpl: DatabaseRepositoryImpl) : DatabaseRepository
}
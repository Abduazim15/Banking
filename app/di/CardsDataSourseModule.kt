package uz.gita.lesson40.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.lesson40.data.datasource.CardsDataSourse
import uz.gita.lesson40.data.datasource.CardsDataSourseImpl

@Module
@InstallIn(SingletonComponent::class)
interface CardsDataSourceModule {
    @Binds
    fun bind(cardsDataSourceImpl: CardsDataSourseImpl):CardsDataSourse
}
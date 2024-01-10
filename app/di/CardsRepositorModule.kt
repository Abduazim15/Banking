package uz.gita.lesson40.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.lesson40.data.repository.CardsRepository
import uz.gita.lesson40.data.repository.CardsRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface CardsRepositorModule {
    @Binds
    fun bind(cardsRepositoryImpl: CardsRepositoryImpl):CardsRepository
}
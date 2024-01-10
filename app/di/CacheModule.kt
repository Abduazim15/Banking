package uz.gita.lesson40.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.lesson40.data.settings.Settings
import uz.gita.lesson40.data.settings.SettingsImpl

@Module
@InstallIn(SingletonComponent::class)
interface CacheModule {
    @Binds
    fun bindSettings(settingsImpl: SettingsImpl): Settings
}
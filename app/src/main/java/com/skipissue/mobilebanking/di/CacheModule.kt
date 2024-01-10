package com.skipissue.mobilebanking.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.skipissue.mobilebanking.data.settings.Settings
import com.skipissue.mobilebanking.data.settings.SettingsImpl

@Module
@InstallIn(SingletonComponent::class)
interface CacheModule {
    @Binds
    fun bindSettings(settingsImpl: SettingsImpl): Settings
}
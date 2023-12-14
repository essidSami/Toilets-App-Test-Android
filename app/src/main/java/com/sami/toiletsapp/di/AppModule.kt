package com.sami.toiletsapp.di

import com.sami.toiletsapp.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

const val TOILETS_KEY = "BASE_URL"

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Named(TOILETS_KEY)
    fun provideToiletsKey(): String = BuildConfig.BASE_URL

}

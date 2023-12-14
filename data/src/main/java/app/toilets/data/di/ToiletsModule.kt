package app.toilets.data.di

import app.toilets.data.repository.ToiletRepositoryImpl
import app.toilets.data.source.remote.ToiletsApi
import app.toilets.domain.repository.ToiletRepository
import dagger.Provides

class ToiletsModule {

    @Provides
    fun provideToiletRepository(
        api: ToiletsApi
    ): ToiletRepository =
        ToiletRepositoryImpl(api = api)
}
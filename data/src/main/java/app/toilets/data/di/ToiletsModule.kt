package app.toilets.data.di

import android.app.Application
import app.toilets.data.location.DefaultLocationTracker
import app.toilets.data.repository.ToiletRepositoryImpl
import app.toilets.domain.location.LocationTracker
import app.toilets.domain.repository.ToiletRepository
import app.toilets.domain.usecases.GetCurrentLocationUseCase
import app.toilets.domain.usecases.GetCurrentLocationUseCaseImp
import app.toilets.domain.usecases.GetToiletsUseCase
import app.toilets.domain.usecases.GetToiletsUseCaseImp
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ToiletsModule {

    @Binds
    @Singleton
    fun provideToiletRepository(impl: ToiletRepositoryImpl): ToiletRepository

    @Binds
    @Singleton
    fun provideLocationRepository(impl: DefaultLocationTracker): LocationTracker


    @Binds
    @Singleton
    fun provideGetToiletsUseCase(impl: GetToiletsUseCaseImp): GetToiletsUseCase

    @Binds
    @Singleton
    fun provideGetCurrentLocationUseCase(impl: GetCurrentLocationUseCaseImp): GetCurrentLocationUseCase
}
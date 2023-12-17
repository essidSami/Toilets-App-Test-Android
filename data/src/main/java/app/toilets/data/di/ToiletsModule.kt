package app.toilets.data.di

import android.app.Application
import android.content.Context
import app.toilets.data.location.DefaultLocationTracker
import app.toilets.data.repository.ToiletRepositoryImpl
import app.toilets.data.source.remote.ToiletsApi
import app.toilets.domain.location.LocationTracker
import app.toilets.domain.repository.ToiletRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ToiletsModule {

    @Provides
    @Singleton
    fun provideToiletRepository(
        api: ToiletsApi
    ): ToiletRepository =
        ToiletRepositoryImpl(api = api)

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(app: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }

    @Provides
    @Singleton
    fun provideLocationRepository(
        locationClient: FusedLocationProviderClient,
        @ApplicationContext context: Context
    ): LocationTracker =
        DefaultLocationTracker(
            locationClient = locationClient,
            context = context
        )
}
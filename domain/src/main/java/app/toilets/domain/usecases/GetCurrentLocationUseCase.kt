package app.toilets.domain.usecases

import android.location.Location
import app.toilets.domain.location.LocationTracker
import javax.inject.Inject

class GetCurrentLocationUseCase @Inject constructor(private val repository: LocationTracker) {

    suspend operator fun invoke(): Location? = repository.getCurrentLocation()
}
package app.toilets.domain.usecases

import android.location.Location
import app.toilets.domain.model.Toilet
import app.toilets.domain.repository.ToiletRepository
import app.toilets.domain.util.Resource
import javax.inject.Inject

class GetToiletsUseCase @Inject constructor(private val repository: ToiletRepository) {

    suspend operator fun invoke(
        start: Int,
        currentLocation: Location,
        geoFilter: String?
    ): Resource<List<Toilet>> =
        repository.getToilets(
            start = start,
            currentLocation = currentLocation,
            geoFilter = geoFilter
        )
}
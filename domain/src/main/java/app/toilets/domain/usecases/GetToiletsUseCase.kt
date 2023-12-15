package app.toilets.domain.usecases

import android.location.Location
import app.toilets.domain.model.Toilet
import app.toilets.domain.repository.ToiletRepository
import app.toilets.domain.util.Resource
import javax.inject.Inject

class GetToiletsUseCase @Inject constructor(private val repository: ToiletRepository) {

    suspend operator fun invoke(
        dataSet: String,
        start: Int,
        rows: Int,
        currentLocation: Location
    ): Resource<List<Toilet>> =
        repository.getToilets(
            dataSet = dataSet,
            start = start,
            rows = rows,
            currentLocation = currentLocation
        )
}
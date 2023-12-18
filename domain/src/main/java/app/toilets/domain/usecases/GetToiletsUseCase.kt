package app.toilets.domain.usecases

import android.location.Location
import app.toilets.domain.model.Toilet

interface GetToiletsUseCase {
    suspend operator fun invoke(
        start: Int,
        currentLocation: Location,
        geoFilter: String?
    ): Result<List<Toilet>>
}
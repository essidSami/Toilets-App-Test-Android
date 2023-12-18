package app.toilets.domain.repository

import android.location.Location
import app.toilets.domain.model.Toilet

interface ToiletRepository {
    suspend fun getToilets(
        start: Int,
        currentLocation: Location,
        geoFilter: String?
    ): Result<List<Toilet>>
}
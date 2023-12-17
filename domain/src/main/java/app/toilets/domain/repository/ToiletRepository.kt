package app.toilets.domain.repository

import android.location.Location
import app.toilets.domain.model.Toilet
import app.toilets.domain.util.Resource

interface ToiletRepository {
    suspend fun getToilets(
        start: Int,
        currentLocation: Location,
        geoFilter: String?
    ): Resource<List<Toilet>>

//    suspend fun getToiletInfo(recordId: String): Resource<Toilet>
}
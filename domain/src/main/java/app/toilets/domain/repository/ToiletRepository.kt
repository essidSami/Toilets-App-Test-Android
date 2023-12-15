package app.toilets.domain.repository

import android.location.Location
import app.toilets.domain.model.Toilet
import app.toilets.domain.util.Resource

interface ToiletRepository {
    suspend fun getToilets(dataSet: String, start: Int, rows: Int, currentLocation: Location): Resource<List<Toilet>>
}
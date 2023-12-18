package app.toilets.data.repository

import android.location.Location
import app.toilets.data.mapper.toToilet
import app.toilets.data.source.remote.ToiletsApi
import app.toilets.domain.model.Toilet
import app.toilets.domain.repository.ToiletRepository
import javax.inject.Inject

const val KEY_DATA_SET = "sanisettesparis2011"
const val KEY_ROWS = 20
const val KEY_KM = "10000"

class ToiletRepositoryImpl @Inject constructor(
    private val api: ToiletsApi
) : ToiletRepository {
    override suspend fun getToilets(
        start: Int,
        currentLocation: Location,
        geoFilter: String?
    ): Result<List<Toilet>> {
        return runCatching {
            api.getToilets(
                dataSet = KEY_DATA_SET,
                start = start,
                rows = KEY_ROWS,
                geoFilter = geoFilter?.let { "$it,$KEY_KM" }
            ).records?.map {
                it.toToilet(currentLocation = currentLocation)
            } ?: emptyList()
        }
    }
}
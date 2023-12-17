package app.toilets.data.repository

import android.location.Location
import app.toilets.data.mapper.toToilet
import app.toilets.data.source.remote.ToiletsApi
import app.toilets.domain.model.Toilet
import app.toilets.domain.repository.ToiletRepository
import app.toilets.domain.util.Resource
import com.google.gson.Gson
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

const val KEY_DATA_SET = "sanisettesparis2011"
const val KEY_ROWS = 20

class ToiletRepositoryImpl @Inject constructor(
    private val api: ToiletsApi,
//    private val dataStoreRepository: DataStoreOperations
) : ToiletRepository {
    override suspend fun getToilets(
        start: Int,
        currentLocation: Location,
        geoFilter: String?
    ): Resource<List<Toilet>> {
        return try {
            Resource.Success(
                data = api.getToilets(
                    dataSet = KEY_DATA_SET,
                    start = start,
                    rows = KEY_ROWS,
                    geoFilter = geoFilter?.let { "$it,10000" }
                ).records?.map { it.toToilet(currentLocation = currentLocation) }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(message = e.message ?: "An unknown error occurred.")
        }
    }

//    override suspend fun getToiletInfo(recordId: String): Resource<Toilet> {
//        return try {
//            var currentLocation: Location?
//            dataStoreRepository.read(PreferencesKeys.currentLocationKey).collectLatest {
//                currentLocation = Gson().fromJson(it, Location::class.java)
//            }
//
//            Resource.Success(
//                data = api.getToiletInfo(
//                    dataSet = KEY_DATA_SET,
//                    recordId = recordId
//                ).records?.first()?.toToilet(currentLocation = Location(""))
//            )
//        } catch (e: Exception) {
//            e.printStackTrace()
//            Resource.Error(message = e.message ?: "An unknown error occurred.")
//        }
//    }
}
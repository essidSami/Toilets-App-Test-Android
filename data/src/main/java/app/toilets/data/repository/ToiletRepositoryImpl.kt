package app.toilets.data.repository

import app.toilets.data.mapper.toToiletList
import app.toilets.data.source.remote.ToiletsApi
import app.toilets.domain.model.ToiletList
import app.toilets.domain.repository.ToiletRepository
import app.toilets.domain.util.Resource
import javax.inject.Inject

class ToiletRepositoryImpl @Inject constructor(
    private val api: ToiletsApi
) : ToiletRepository {
    override suspend fun getToilets(dataSet: String, start: Int, rows: Int): Resource<ToiletList> {
        return try {
            Resource.Success(
                data = api.getToilets(
                    dataSet = dataSet,
                    start = start,
                    rows = rows
                ).toToiletList()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(message = e.message ?: "An unknown error occurred.")
        }
    }
}
package app.toilets.data.repository

import app.toilets.data.mapper.toToiletList
import app.toilets.data.source.remote.ToiletsApi
import app.toilets.domain.model.ToiletList
import app.toilets.domain.repository.ToiletRepository
import app.toilets.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ToiletRepositoryImpl @Inject constructor(
    private val api: ToiletsApi
) : ToiletRepository {
    override fun getToilets(dataSet: String, start: Int, rows: Int): Flow<Resource<ToiletList>> =
        flow {
            try {
                emit(Resource.Loading)
                emit(
                    Resource.Success(
                        data = api.getToilets(
                            dataSet = dataSet,
                            start = start,
                            rows = rows
                        ).toToiletList()
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
                Resource.Error(e.message ?: "An unknown error occurred.")
            }
        }
}
package app.toilets.domain.repository

import app.toilets.domain.model.ToiletList
import app.toilets.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface ToiletRepository {
    suspend fun getToilets(dataSet: String, start: Int, rows: Int): Resource<ToiletList>
}
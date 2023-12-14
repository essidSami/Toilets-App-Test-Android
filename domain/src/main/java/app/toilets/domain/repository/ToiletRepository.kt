package app.toilets.domain.repository

import app.toilets.domain.model.ToiletList
import app.toilets.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface ToiletRepository {
    fun getToilets(dataSet: String, start: Int, rows: Int): Flow<Resource<ToiletList>>
}
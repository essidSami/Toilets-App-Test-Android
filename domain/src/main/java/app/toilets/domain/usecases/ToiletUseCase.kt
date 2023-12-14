package app.toilets.domain.usecases

import app.toilets.domain.model.ToiletList
import app.toilets.domain.repository.ToiletRepository
import app.toilets.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ToiletUseCase @Inject constructor(private val repository: ToiletRepository) {

    operator fun invoke(dataSet: String, start: Int, rows: Int): Flow<Resource<ToiletList>> =
        repository.getToilets(dataSet = dataSet, start = start, rows = rows)
}
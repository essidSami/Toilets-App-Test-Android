package app.toilets.domain.usecases

import app.toilets.domain.model.ToiletList
import app.toilets.domain.repository.ToiletRepository
import app.toilets.domain.util.Resource
import javax.inject.Inject

class GetToiletsUseCase @Inject constructor(private val repository: ToiletRepository) {

    suspend operator fun invoke(dataSet: String, start: Int, rows: Int): Resource<ToiletList> =
        repository.getToilets(dataSet = dataSet, start = start, rows = rows)
}
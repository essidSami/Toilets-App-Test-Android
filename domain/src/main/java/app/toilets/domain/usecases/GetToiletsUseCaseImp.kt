package app.toilets.domain.usecases

import android.location.Location
import app.toilets.domain.model.Toilet
import app.toilets.domain.repository.ToiletRepository
import app.toilets.domain.util.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetToiletsUseCaseImp @Inject constructor(
    private val repository: ToiletRepository,
    @IoDispatcher val ioDispatcher: CoroutineDispatcher
): GetToiletsUseCase {

    override suspend operator fun invoke(
        start: Int,
        currentLocation: Location,
        geoFilter: String?
    ): Result<List<Toilet>> = withContext(ioDispatcher) {
        repository.getToilets(
            start = start,
            currentLocation = currentLocation,
            geoFilter = geoFilter
        )
    }
}
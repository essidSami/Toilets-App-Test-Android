package app.toilets.domain.usecases

import android.location.Location
import app.toilets.domain.location.LocationTracker
import app.toilets.domain.util.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCurrentLocationUseCaseImp @Inject constructor(
    private val repository: LocationTracker,
    @IoDispatcher val ioDispatcher: CoroutineDispatcher
) : GetCurrentLocationUseCase {

    override suspend operator fun invoke(): Location? = withContext(ioDispatcher) {
        repository.getCurrentLocation()
    }
}
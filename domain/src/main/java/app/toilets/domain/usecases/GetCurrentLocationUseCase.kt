package app.toilets.domain.usecases

import android.location.Location

interface GetCurrentLocationUseCase {
    suspend operator fun invoke(): Location?
}
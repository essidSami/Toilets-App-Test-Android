package app.toilets.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.toilets.domain.usecases.GetCurrentLocationUseCase
import app.toilets.domain.usecases.GetToiletsUseCase
import app.toilets.domain.util.Resource
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getToiletsUseCase: GetToiletsUseCase,
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase
) : ViewModel() {

    var state by mutableStateOf(ToiletsState())
        private set

    var displayMode by mutableStateOf(0)

    fun loadToilets(start: Int, geoFilter: String? = null) {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            getCurrentLocationUseCase()?.let { location ->
                when (val result = getToiletsUseCase(
                    start = start,
                    currentLocation = location,
                    geoFilter = geoFilter
                )) {
                    is Resource.Success -> {
                        state = state.copy(
                            toiletList = state.toiletList.plus(result.data ?: listOf()),
                            isLoading = false,
                            error = null,
                            endReached = result.data?.isEmpty() == true,
                            currentLocation = LatLng(location.latitude, location.longitude)
                        )
                    }

                    else -> {
                        state = state.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }

            } ?: kotlin.run {
                state = state.copy(
                    isLoading = false,
                    error = "Couldn't retrieve location. Make sure to grant permission and enable GPS."
                )
            }
        }
    }
}
package app.toilets.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.toilets.domain.usecases.GetCurrentLocationUseCase
import app.toilets.domain.usecases.GetToiletsUseCase
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

    init {
        loadToilets(start = 0)
    }

    fun loadToilets(start: Int, geoFilter: String? = null) {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            getCurrentLocationUseCase()?.let { location ->
                getToiletsUseCase(
                    start = start,
                    currentLocation = location,
                    geoFilter = geoFilter
                ).onSuccess { toilets ->
                    state = state.copy(
                        toiletList = state.toiletList.plus(toilets),
                        isLoading = false,
                        error = null,
                        endReached = toilets.isEmpty(),
                        currentLocation = LatLng(location.latitude, location.longitude)
                    )
                }.onFailure {
                    state = if (state.toiletList.isEmpty()){
                        state.copy(
                            toiletList = emptyList(),
                            isLoading = false,
                            error = "An unknown error occurred."
                        )
                    }else{
                        state.copy(
                            toiletList = state.toiletList,
                            isLoading = false,
                            error = null,
                            endReached = true,
                            currentLocation = LatLng(location.latitude, location.longitude)
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
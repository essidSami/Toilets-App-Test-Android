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

    fun loadToilets(start: Int) {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            getCurrentLocationUseCase()?.let { location ->
                when (val result = getToiletsUseCase(
                    dataSet = "sanisettesparis2011",
                    start = start,
                    rows = 10,
                    currentLocation = location
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

                    is Resource.Error -> {
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
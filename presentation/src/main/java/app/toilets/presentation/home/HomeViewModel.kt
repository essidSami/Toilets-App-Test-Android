package app.toilets.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.toilets.domain.usecases.GetCurrentLocationUseCase
import app.toilets.domain.usecases.GetToiletsUseCase
import app.toilets.domain.util.Resource
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

    fun loadToilets() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            getCurrentLocationUseCase()?.let { location ->
                when (val result = getToiletsUseCase(dataSet = "sanisettesparis2011", start = 0, rows = 10)) {
                    is Resource.Success -> {
                        state = state.copy(
                            toiletList = result.data,
                            isLoading = false,
                            error = null
                        )
                    }

                    is Resource.Error -> {
                        state = state.copy(
                            toiletList = null,
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
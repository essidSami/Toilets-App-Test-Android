package app.toilets.presentation.home

import android.location.Location
import app.toilets.domain.usecases.GetCurrentLocationUseCase
import app.toilets.domain.usecases.GetToiletsUseCase
import app.toilets.presentation.MainCoroutineRule
import app.toilets.presentation.toilets
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations.openMocks
import org.mockito.kotlin.given

@DelicateCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private val getToiletsUseCase = mock<GetToiletsUseCase>()

    @Mock
    private val getCurrentLocationUseCase = mock<GetCurrentLocationUseCase>()

    @Mock
    private val currentLocation = mock<Location>()

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        openMocks(this)
        viewModel = HomeViewModel(getToiletsUseCase, getCurrentLocationUseCase)
    }

    @Test
    fun `loadToilets should update state correctly on success`() = runBlocking {
        //Given
        given(currentLocation.longitude).willReturn(2.26)
        given(currentLocation.altitude).willReturn(48.86)
        given(getCurrentLocationUseCase()).willReturn(currentLocation)
        given(
            getToiletsUseCase(
                start = 0,
                currentLocation = currentLocation,
                geoFilter = null
            )
        ).willReturn(Result.success(toilets))

        //When
        viewModel.loadToilets(start = 0)

        //Then
        assertEquals(toilets, viewModel.state.toiletList)
    }

    @Test
    fun `loadToilets should update state on error`() = runBlocking {
        //Given
        given(currentLocation.longitude).willReturn(2.26)
        given(currentLocation.altitude).willReturn(48.86)
        given(getCurrentLocationUseCase()).willReturn(currentLocation)
        given(
            getToiletsUseCase(
                start = 0,
                currentLocation = currentLocation,
                geoFilter = null
            )
        ).willReturn(Result.failure(exception = Throwable()))

        //When
        viewModel.loadToilets(start = 0)

        //Then
        assertEquals("An unknown error occurred.", viewModel.state.error)
        assertFalse(viewModel.state.isLoading)
    }

    @Test
    fun `loadToilets when GPS not activated`() = runBlocking {
        //Given
        given(getCurrentLocationUseCase()).willReturn(null)

        //When
        viewModel.loadToilets(start = 0)

        //Then
        assertEquals(
            "Couldn't retrieve location. Make sure to grant permission and enable GPS.",
            viewModel.state.error
        )
        assertFalse(viewModel.state.isLoading)
    }
}
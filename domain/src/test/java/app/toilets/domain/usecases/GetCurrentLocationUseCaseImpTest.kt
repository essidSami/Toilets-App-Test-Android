package app.toilets.domain.usecases

import android.location.Location
import app.toilets.domain.TestDispatcher.getTestDispatcher
import app.toilets.domain.location.LocationTracker
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.given

class GetCurrentLocationUseCaseImpTest {

    @Mock
    private lateinit var locationTracker: LocationTracker

    private lateinit var getCurrentLocationUseCaseImp: GetCurrentLocationUseCaseImp

    @Mock
    private val currentLocation = Mockito.mock<Location>()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        getCurrentLocationUseCaseImp = GetCurrentLocationUseCaseImp(
            repository = locationTracker,
            ioDispatcher = getTestDispatcher()
        )
    }

    @Test
    fun `getToilets returns Success Resource when repository returns data`() = runTest {

        // Given
        given(currentLocation.longitude).willReturn(2.26)
        given(currentLocation.altitude).willReturn(48.86)

        // Mock the behavior of getCurrentLocation() in the repository
        given(locationTracker.getCurrentLocation()).willReturn(currentLocation)

        // When
        val result = getCurrentLocationUseCaseImp()

        // Then
        assertEquals(currentLocation, result)
    }

    @Test
    fun `test getting current location when unavailable`() = runTest {

        // Given
        given(locationTracker.getCurrentLocation()).willReturn(null)

        // When
        val result = getCurrentLocationUseCaseImp()

        //Then
        assertEquals(null, result)
    }
}
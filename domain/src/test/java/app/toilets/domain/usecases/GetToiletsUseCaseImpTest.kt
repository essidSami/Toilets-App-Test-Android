package app.toilets.domain.usecases

import android.location.Location
import app.toilets.domain.TestDispatcher.getTestDispatcher
import app.toilets.domain.repository.ToiletRepository
import app.toilets.domain.toilets
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations.openMocks
import org.mockito.kotlin.given

class GetToiletsUseCaseImpTest {

    @Mock
    private lateinit var repository: ToiletRepository

    private lateinit var getToiletsUseCaseImp: GetToiletsUseCaseImp

    @Mock
    private val currentLocation = mock<Location>()

    @Before
    fun setup() {
        openMocks(this)
        getToiletsUseCaseImp = GetToiletsUseCaseImp(
            repository = repository,
            ioDispatcher = getTestDispatcher()
        )
        given(currentLocation.longitude).willReturn(2.26)
        given(currentLocation.altitude).willReturn(48.86)
    }

    @Test
    fun `getToilets returns Success Resource when repository returns data`() = runTest {

        // Given
        given(
            repository.getToilets(
                start = 0,
                currentLocation = currentLocation,
                geoFilter = null
            )
        ).willReturn(Result.success(toilets))

        // When
        val actualResult = getToiletsUseCaseImp(start = 0, currentLocation = currentLocation, null)

        //Then
        assertEquals(toilets, actualResult.getOrNull())
    }

    @Test
    fun `getToilets returns Error Resource when repository throws an exception`() = runTest {
        // Given
        given(
            repository.getToilets(
                start = 0,
                currentLocation = currentLocation,
                geoFilter = null
            )
        ).willReturn(Result.failure(Throwable("Error fetching toilets")))

        // WHEN
        val result = getToiletsUseCaseImp(
            start = 0,
            currentLocation = currentLocation,
            geoFilter = null
        )

        //Then
        assertTrue(result.isFailure)
        assertEquals("Error fetching toilets", result.exceptionOrNull()?.message)
    }
}
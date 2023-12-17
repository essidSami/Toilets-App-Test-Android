package app.toilets.domain.usecases

import android.location.Location
import app.toilets.domain.repository.ToiletRepository
import app.toilets.domain.toilets
import app.toilets.domain.util.Resource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations.openMocks
import org.mockito.kotlin.given

class GetToiletsUseCaseTest {

    @Mock
    private lateinit var repository: ToiletRepository

    private lateinit var getToiletsUseCase: GetToiletsUseCase

    @Mock
    private val currentLocation = Mockito.mock<Location>()

    @Before
    fun setup() {
        openMocks(this)
        getToiletsUseCase = GetToiletsUseCase(repository)
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
        ).willReturn(Resource.Success(toilets))

        // When
        val actualResult = getToiletsUseCase(start = 0, currentLocation = currentLocation, null)

        //Then
        assertEquals(toilets, actualResult.data)
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
        ).willReturn(Resource.Error("Error fetching toilets"))

        // WHEN
        val result = getToiletsUseCase(
            start = 0,
            currentLocation = currentLocation,
            geoFilter = null
        )

        //Then
        assert(result is Resource.Error)
    }
}
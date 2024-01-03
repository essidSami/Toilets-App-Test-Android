package app.toilets.data.repository

import android.location.Location
import app.toilets.data.source.remote.ToiletsApi
import app.toilets.data.toilets
import app.toilets.data.wsResponse
import app.toilets.domain.repository.ToiletRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations.openMocks
import org.mockito.exceptions.base.MockitoException
import org.mockito.kotlin.given

class ToiletRepositoryTest {

    private val apiService = mock<ToiletsApi>()

    private lateinit var toiletRepository: ToiletRepository

    private val currentLocation = mock<Location>()

    @Before
    fun setup() {
        openMocks(this)
        toiletRepository = ToiletRepositoryImpl(apiService)
        given(currentLocation.longitude).willReturn(2.26)
        given(currentLocation.altitude).willReturn(48.86)
    }

    @Test
    fun `test getToilets success`() = runTest {
        //Given
        given(
            apiService.getToilets(
                dataSet = "sanisettesparis2011",
                start = 0,
                rows = 20,
                geoFilter = null
            )
        ).willReturn(wsResponse)

        // When
        val actualResult = toiletRepository.getToilets(
            start = 0,
            currentLocation = currentLocation,
            geoFilter = null
        )

        //Then
        assertEquals(toilets, actualResult.getOrNull())
    }

    @Test
    fun `test getToilets fails`() = runTest {
        //Given
        given(
            apiService.getToilets(
                dataSet = "sanisettesparis2011",
                start = 0,
                rows = 20,
                geoFilter = null
            )
        ).willThrow(MockitoException("Can't retrieve toilets"))

        // When
        val actualResult = toiletRepository.getToilets(
            start = 0,
            currentLocation = currentLocation,
            geoFilter = null
        )

        //Then
        assertTrue(actualResult.isFailure)
        assertEquals("Can't retrieve toilets",actualResult.exceptionOrNull()?.message)
    }
}
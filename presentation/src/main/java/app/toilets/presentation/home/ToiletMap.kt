package app.toilets.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.toilets.domain.model.Toilet
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun ToiletMap(modifier: Modifier, location: LatLng, toiletList: List<Toilet>) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location, 15f)
    }
    GoogleMap(
        cameraPositionState = cameraPositionState,
        modifier = modifier,
        properties = MapProperties(isMyLocationEnabled = true),
        uiSettings = MapUiSettings(compassEnabled = true)
    ) {
        toiletList.map { toilet ->
            toilet.geoShape?.let { latLang ->
                Marker(
                    state = rememberMarkerState(position = LatLng(latLang.second, latLang.first)),
                    title = toilet.address,
                    icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
                )
            }
        }
    }
}
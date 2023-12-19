package app.toilets.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import app.toilets.domain.model.Toilet
import app.toilets.presentation.R
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun ToiletMap(
    modifier: Modifier,
    currentLocation: LatLng,
    toiletList: List<Toilet>,
    onCameraChangePosition: (String) -> Unit,
    onClickItem: (Toilet) -> Unit
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(currentLocation, 15f)
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
                    icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED),
                    title = "${toilet.address} | ${
                        if (toilet.distance >= 1000) {
                            stringResource(id = R.string.txt_km).format(toilet.distance * 0.001)
                        } else {
                            stringResource(id = R.string.txt_meter).format(toilet.distance)
                        }
                    }",
                    onInfoWindowClick = { onClickItem(toilet) }
                )
            }
        }
    }
    trackMapInteraction(
        cameraPositionState = cameraPositionState,
        onCameraChangePosition = onCameraChangePosition
    )
}

@Composable
fun trackMapInteraction(
    cameraPositionState: CameraPositionState,
    onCameraChangePosition: (String) -> Unit
) {
    // store the initial position of the camera
    var initialCameraPosition by remember { mutableStateOf(cameraPositionState.position) }

    // called when the camera just starts moving
    val onMapCameraMoveStart: (cameraPosition: CameraPosition) -> Unit = {
        // store the camera's position when map started moving
        initialCameraPosition = it
    }

    // called when the map camera stops moving
    val onMapCameraIdle: (cameraPosition: CameraPosition) -> Unit = { newCameraPosition ->

        initialCameraPosition = newCameraPosition
        onCameraChangePosition("${cameraPositionState.position.target.latitude},${cameraPositionState.position.target.longitude}")
    }

    LaunchedEffect(key1 = cameraPositionState.isMoving) {
        if (cameraPositionState.isMoving) onMapCameraMoveStart(cameraPositionState.position)
        else onMapCameraIdle(cameraPositionState.position)
    }
}

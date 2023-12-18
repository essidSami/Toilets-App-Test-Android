package app.toilets.presentation.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.toilets.domain.model.Toilet
import app.toilets.presentation.R
import app.toilets.presentation.util.toiletList
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
fun DetailsScreen(
    toiletInfo: Toilet,
    onBackPressed: () -> Unit
) {
    toiletInfo.apply {
        val cameraPositionState = rememberCameraPositionState {
            position =
                CameraPosition.fromLatLngZoom(LatLng(geoShape?.second!!, geoShape?.first!!), 15f)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            IconButton(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(8.dp),
                onClick = {
                    onBackPressed()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.img_back),
                    contentDescription = "",
                    tint = colorResource(id = R.color.black),
                    modifier = Modifier.size(24.dp)
                )
            }

            Text(
                text = gestionnaire,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Start,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.img_address),
                    contentDescription = "time",
                    modifier = Modifier.size(18.dp)
                )

                Text(
                    text = "$address, $arrondissement",
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .weight(1F)
                        .padding(start = 12.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.img_time),
                    contentDescription = "time",
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = horaire,
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(start = 12.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_handicapped),
                    contentDescription = "handicapped",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(
                            if (accesPmr) colorResource(id = R.color.caribbean_green) else colorResource(
                                id = R.color.persimmon
                            )
                        )
                        .padding(6.dp),
                )
                Spacer(modifier = Modifier.width(6.dp))
                Image(
                    painter = painterResource(id = R.drawable.img_baby),
                    contentDescription = "baby",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(
                            if (relaisBebe) colorResource(id = R.color.caribbean_green) else colorResource(
                                id = R.color.persimmon
                            )
                        )
                        .padding(6.dp),
                )
                Spacer(modifier = Modifier.width(6.dp))
                Box(
                    modifier = Modifier
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(15.dp)
                        )
                ) {
                    Text(
                        text = if (distance >= 1) {
                            stringResource(id = R.string.txt_km).format(distance)
                        } else {
                            stringResource(id = R.string.txt_meter).format(distance)
                        },
                        style = MaterialTheme.typography.titleSmall,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(vertical = 6.dp, horizontal = 12.dp)
                    )
                }
            }

            GoogleMap(
                cameraPositionState = cameraPositionState,
                modifier = Modifier.padding(top = 16.dp),
                properties = MapProperties(isMyLocationEnabled = true),
                uiSettings = MapUiSettings(compassEnabled = true)
            ) {
                geoShape?.let { latLang ->
                    Marker(
                        state = rememberMarkerState(
                            position = LatLng(
                                latLang.second,
                                latLang.first
                            )
                        ),
                        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED),
                        title = "$address | ${
                            stringResource(id = R.string.txt_km).format(
                                distance
                            )
                        }"
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewDetailsScreen() {
    DetailsScreen(toiletInfo = toiletList[0]) {}
}
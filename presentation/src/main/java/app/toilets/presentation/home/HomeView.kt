package app.toilets.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import app.toilets.domain.model.Toilet
import app.toilets.presentation.R

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onClickItem: (Toilet) -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val state = viewModel.state
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            SegmentedControl(
                defaultSelectedItemIndex = viewModel.displayMode,
                items = listOf(
                    Pair(
                        stringResource(id = R.string.txt_list),
                        painterResource(id = R.drawable.img_list)
                    ),
                    Pair(
                        stringResource(id = R.string.txt_map),
                        painterResource(id = R.drawable.img_map)
                    )
                )
            ) {
                viewModel.displayMode = it
            }
            state.toiletList.let { toilets ->
                when (viewModel.displayMode) {
                    0 -> {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            itemsIndexed(toilets) { index, item ->
                                if (index >= toilets.size - 1 && !state.endReached && !state.isLoading) {
                                    viewModel.loadToilets(start = toilets.size + 1)
                                }
                                ToiletCard(item, onClickItem = onClickItem)
                            }
                        }
                    }

                    else -> {
                        state.currentLocation?.let { latLng ->
                            ToiletMap(
                                modifier = Modifier.weight(1F),
                                location = latLng,
                                toiletList = toilets
                            ) {
                                viewModel.loadToilets(
                                    start = toilets.size + 1,
                                    geoFilter = it
                                )
                            }
                        }
                    }
                }
            }
        }
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }

        state.error?.let { error ->
            Text(
                text = error,
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
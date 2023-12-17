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
import androidx.compose.ui.tooling.preview.Preview
import app.toilets.domain.model.Toilet
import app.toilets.presentation.R
import app.toilets.presentation.util.toiletList

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onClickItem: (Toilet) -> Unit
) {
    val state = viewModel.state
    HomeViewContent(
        state = state,
        displayMode = viewModel.displayMode,
        onClickItem = onClickItem,
        onChangeMode = { viewModel.displayMode = it },
        onLoadMore = { start, geoFilter ->
            viewModel.loadToilets(
                start = start,
                geoFilter = geoFilter
            )
        }
    )
}

@Composable
fun HomeViewContent(
    state: ToiletsState,
    displayMode: Int,
    onClickItem: (Toilet) -> Unit,
    onChangeMode: (Int) -> Unit,
    onLoadMore: (Int, String?) -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            SegmentedControl(
                defaultSelectedItemIndex = displayMode,
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
            ) { onChangeMode(it) }
            state.toiletList.let { toilets ->
                when (displayMode) {
                    0 -> {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            itemsIndexed(toilets) { index, item ->
                                if (index >= toilets.size - 1 && !state.endReached && !state.isLoading) {
                                    onLoadMore(toilets.size + 1, null)
                                }
                                ToiletCard(item, onClickItem = onClickItem)
                            }
                        }
                    }

                    else -> {
                        state.currentLocation?.let { latLng ->
                            ToiletMap(
                                modifier = Modifier.weight(1F),
                                currentLocation = latLng,
                                toiletList = toilets
                            ) {
                                onLoadMore(toilets.size + 1, it)
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

@Preview
@Composable
fun PreviewToiletsList() {
    HomeViewContent(
        state = ToiletsState(toiletList),
        displayMode = 0,
        onClickItem = {},
        onChangeMode = {},
        onLoadMore = { _, _ -> })
}

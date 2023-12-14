package app.toilets.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val state = viewModel.state
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            state.toiletList.let { toilets ->
                LazyColumn(modifier = Modifier.fillMaxHeight()) {
                    itemsIndexed(toilets) { index, item ->
                        if (index >= toilets.size - 1 && !state.endReached && !state.isLoading) {
                            viewModel.loadToilets(toilets.size + 1)
                        }
                        ToiletCard(item)
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
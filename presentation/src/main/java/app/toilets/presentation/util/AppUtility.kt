package app.toilets.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import app.toilets.presentation.R

@Composable
fun Float.calculateDistance(): String {
    return if (this >= 1) {
        stringResource(id = R.string.txt_km).format(String.format("%.3f", this))
    } else {
        stringResource(id = R.string.txt_meter).format(String.format("%.0f", this))
    }
}
package app.toilets.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.toilets.presentation.R

@Composable
fun AgainPermissionView(onOkClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = stringResource(id = R.string.txt_gps_not_active),
                color = Color.Red,
                textAlign = TextAlign.Center
            )

            Button(
                onClick = { onOkClick() },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = stringResource(id = R.string.txt_btn_permission).uppercase())
            }
        }
    }
}
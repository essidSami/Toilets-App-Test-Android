package app.toilets.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import app.toilets.domain.model.Toilet
import app.toilets.presentation.R
import app.toilets.presentation.util.calculateDistance

@Composable
fun ToiletCard(toilet: Toilet, modifier: Modifier = Modifier) {
    toilet.apply {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            modifier = modifier.padding(16.dp)
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = gestionnaire,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )

                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.img_address),
                        contentDescription = "time",
                        modifier = modifier.size(18.dp)
                    )

                    Text(
                        text = "$address, $arrondissement",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Start,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = modifier
                            .weight(1F)
                            .padding(start = 12.dp)
                    )
                }

                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.img_time),
                        contentDescription = "time",
                        modifier = modifier.size(18.dp)
                    )
                    Text(
                        text = horaire,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        modifier = modifier.padding(start = 12.dp)
                    )
                }

                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
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
                    Spacer(modifier = modifier.width(6.dp))
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
                    Spacer(modifier = modifier.width(6.dp))
                    Box(
                        modifier = modifier.background(
                            color = colorResource(id = R.color.caribbean_green),
                            shape = RoundedCornerShape(15.dp)
                        )
                    ) {
                        Text(
                            text = distance.calculateDistance(),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center,
                            modifier = modifier.padding(vertical = 6.dp, horizontal = 12.dp)
                        )
                    }
                }
            }
        }
    }
}
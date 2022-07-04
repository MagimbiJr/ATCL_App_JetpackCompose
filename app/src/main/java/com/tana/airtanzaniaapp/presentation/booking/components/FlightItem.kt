package com.tana.airtanzaniaapp.presentation.booking.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tana.airtanzaniaapp.R
import com.tana.airtanzaniaapp.data.Flight
import com.tana.airtanzaniaapp.presentation.ui.theme.DottedShape

@Composable
fun FlightItem(
    flight: Flight,
    modifier: Modifier,
    onFlightClick: (Flight) -> Unit,
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { onFlightClick(flight) },
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = modifier
                .padding(12.dp)
        ) {
            LogoAndPrice(modifier, flight)
            Spacer(modifier = modifier.height(16.dp))
            DestinationItem(modifier = modifier, flight = flight)
            Spacer(modifier = modifier.height(6.dp))
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Card(
                    shape = CircleShape,
                    backgroundColor = MaterialTheme.colors.surface.copy(.3f)
                ) {
                    Text(
                        text = flight.departureAirport,
                        modifier = modifier
                            .padding(horizontal = 16.dp, vertical = 4.dp),
                        color = MaterialTheme.colors.onSurface.copy(.3f)
                    )
                }
                Card(
                    shape = CircleShape,
                    backgroundColor = MaterialTheme.colors.surface.copy(.3f)
                ) {
                    Text(
                        text = flight.arrivalAirport,
                        modifier = modifier
                            .padding(horizontal = 16.dp, vertical = 4.dp),
                        color = MaterialTheme.colors.onSurface.copy(.3f)
                    )
                }
            }
            Spacer(modifier = modifier.height(12.dp))
            Text(
                text = flight.flightName,
                modifier = modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onBackground.copy(.3f)
            )
            Spacer(modifier = modifier.height(4.dp))
            Box(modifier = modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(
                    color = MaterialTheme.colors.onSurface.copy(.3f),
                    shape = DottedShape(step = 10.dp)
                )
            )
            TimeDateDetails(modifier = modifier, flight = flight)
        }
    }
}

@Composable
private fun TimeDateDetails(
    modifier: Modifier,
    flight: Flight
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Card(
            shape = CircleShape,
            backgroundColor = MaterialTheme.colors.surface.copy(.3f)
        ) {
            Row(
                modifier = modifier
                    .padding(horizontal = 8.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.calender_icon),
                    contentDescription = "Calender icon",
                    modifier = modifier
                        .size(24.dp),
                    tint = MaterialTheme.colors.onBackground.copy(.3f)
                )
                Text(
                    text = flight.date,
                    color = MaterialTheme.colors.onBackground.copy(.3f)
                )
            }
        }
        Card(
            shape = CircleShape,
            backgroundColor = MaterialTheme.colors.surface.copy(.3f)
        ) {
            Row(
                modifier = modifier
                    .padding(horizontal = 8.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.timer),
                    contentDescription = "Calender icon",
                    modifier = modifier
                        .size(24.dp),
                    tint = MaterialTheme.colors.onBackground.copy(.3f)
                )
                Text(
                    text = flight.flightTime,
                    color = MaterialTheme.colors.onBackground.copy(.3f)
                )
            }
        }
    }
}

@Composable
private fun DestinationItem(
    modifier: Modifier,
    flight: Flight
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = flight.departureTime)
        Spacer(modifier = modifier.width(8.dp))
        Box(
            modifier = modifier
                .size(6.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colors.onSurface.copy(.3f))
        )
        Spacer(modifier = modifier.width(2.dp))
        Box(
            modifier = modifier
                .weight(1f)
                .height(1.dp)
                .background(
                    color = MaterialTheme.colors.onSurface.copy(.3f),
                    shape = DottedShape(step = 10.dp)
                )
        )
        Spacer(modifier = modifier.width(8.dp))
        Image(
            painter = painterResource(id = R.drawable.plane_forward),
            contentDescription = "Plane forward",
            modifier = modifier
                .size(16.dp)
        )
        Spacer(modifier = modifier.width(8.dp))
        Box(
            modifier = modifier
                .weight(1f)
                .height(1.dp)
                .background(
                    color = MaterialTheme.colors.onSurface.copy(.3f),
                    shape = DottedShape(step = 10.dp)
                )
        )
        Spacer(modifier = modifier.width(2.dp))
        Box(
            modifier = modifier
                .size(6.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colors.onSurface.copy(.3f))
        )
        Spacer(modifier = modifier.width(8.dp))
        Text(text = flight.arrivalTime)
    }
}

@Composable
private fun LogoAndPrice(
    modifier: Modifier,
    flight: Flight
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.air_tanzania_splash_logo),
            contentDescription = "Logo",
        )
        Text(
            text = "${flight.price}$",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
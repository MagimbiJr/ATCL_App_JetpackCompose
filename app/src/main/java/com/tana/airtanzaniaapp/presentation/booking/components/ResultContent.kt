package com.tana.airtanzaniaapp.presentation.booking.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tana.airtanzaniaapp.R
import com.tana.airtanzaniaapp.data.Flight
import com.tana.airtanzaniaapp.presentation.booking.SearchFlightUiState
import com.tana.airtanzaniaapp.presentation.ui.theme.DottedShape

@Composable
fun ResultContent(
    uiState: SearchFlightUiState,
    onFlightClick: (Flight) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        ResultContentHeader(
            flight = uiState.result[0],
            modifier = modifier
        )
        Spacer(modifier = modifier.height(12.dp))
        FlightResults(uiState = uiState, modifier = modifier, onFlightClick = onFlightClick)
    }
}


@Composable
fun ResultContentHeader(
    flight: Flight,
    modifier: Modifier,
) {
    Column() {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "From",
                color = MaterialTheme.colors.onBackground.copy(.3f)
            )
            Text(
                text = "To",
                color = MaterialTheme.colors.onBackground.copy(.3f)
            )
        }
        Spacer(modifier = modifier.height(3.dp))
        ATCDivider(
            modifier = modifier,
            departureInfo = flight.flyFrom,
            arrivalInfo = flight.flyTo
        )
    }
    Text(
        text = "1 stop",
        modifier = modifier
            .fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary
    )
}

@Composable
fun FlightResults(
    uiState: SearchFlightUiState,
    onFlightClick: (Flight) -> Unit,
    modifier: Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(uiState.result) { flight -> 
            FlightItem(flight = flight, modifier = modifier, onFlightClick = onFlightClick)
        }
    }
}

@Composable
fun ATCDivider(
    modifier: Modifier,
    departureInfo: String,
    arrivalInfo: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = departureInfo)
        Spacer(modifier = modifier.width(12.dp))
        Box(
            modifier = modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colors.onBackground.copy(.3f))
        )
        Spacer(modifier = modifier.width(3.dp))
        Divider(
            modifier = modifier
                .height(1.dp)
                .weight(1f)
                .background(
                    color = MaterialTheme.colors.onBackground.copy(.3f),
                    shape = DottedShape(step = 10.dp)
                )
        )
        Spacer(modifier = modifier.width(3.dp))
        Image(
            painter = painterResource(id = R.drawable.plane_forward),
            contentDescription = "Flight icon",
            modifier = modifier
                .size(16.dp)
        )
        Spacer(modifier = modifier.width(3.dp))
        Divider(
            modifier = modifier
                .height(1.dp)
                .weight(1f)
                .background(
                    color = MaterialTheme.colors.onBackground.copy(.3f),
                    shape = DottedShape(step = 10.dp)
                )
        )
        Spacer(modifier = modifier.width(3.dp))
        Box(
            modifier = modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colors.onBackground.copy(.3f))
        )
        Spacer(modifier = modifier.width(12.dp))
        Text(text = arrivalInfo)
    }
}

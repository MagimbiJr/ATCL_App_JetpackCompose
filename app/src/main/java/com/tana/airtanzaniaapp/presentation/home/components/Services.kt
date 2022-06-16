package com.tana.airtanzaniaapp.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tana.airtanzaniaapp.R

@Composable
fun Services(
    onBookFlightClick: () -> Unit,
    onRentCarClick: () -> Unit,
    onHotelClick: () -> Unit,
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ServiceItem(
            serviceItemTitle = "Book flight",
            serviceItemImage = R.drawable.plane_small_icon,
            onClick = onBookFlightClick,
            modifier = modifier
        )
        ServiceItem(
            serviceItemTitle = "Rent a car",
            serviceItemImage = R.drawable.car_small_icon,
            onClick = onRentCarClick,
            modifier = modifier
        )
        ServiceItem(
            serviceItemTitle = "Hotels",
            serviceItemImage = R.drawable.hotel_small_icon,
            onClick = onHotelClick,
            modifier = modifier
        )
    }
}

@Composable
fun ServiceItem(
    serviceItemTitle: String,
    serviceItemImage: Int,
    onClick: () -> Unit,
    modifier: Modifier
) {
    Card(
        modifier = modifier
            .width(74.dp)
            .height(76.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.primary.copy(.1f),
                shape = RoundedCornerShape(4.dp)
            )
            .clickable { onClick() },
        shape = RoundedCornerShape(4.dp),
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp
    ) {
        Column(
            modifier = modifier
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = serviceItemImage),
                contentDescription = "Book plane service",
                modifier = modifier
                    .size(25.dp)
            )
            Spacer(modifier = modifier.height(12.dp))
            Text(
                text = serviceItemTitle,
                color = MaterialTheme.colors.onBackground.copy(.5f),
                fontSize = 10.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
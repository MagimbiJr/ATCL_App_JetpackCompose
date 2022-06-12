package com.tana.airtanzaniaapp.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.tana.airtanzaniaapp.data.Destination

@Composable
fun TripItem(
    destination: Destination,
    onClick: () -> Unit,
    modifier: Modifier,
) {
    Box(
        modifier = modifier
            .width(167.dp)
            .height(210.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
    ) {
        AsyncImage(
            model = destination.image,
            contentDescription = destination.name,
            modifier = modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.White.copy(.1f),
                            Color.Black.copy(.6f)
                        )
                    )
                )
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
                .align(Alignment.BottomStart),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column() {
                Text(
                    text = destination.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = destination.travelDays,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W100,
                    color = Color.White
                )
            }
            Text(
                text = "$${destination.price}",
                color = Color.White
                //modifier = modifier.width(40.dp)
            )
        }
    }
}
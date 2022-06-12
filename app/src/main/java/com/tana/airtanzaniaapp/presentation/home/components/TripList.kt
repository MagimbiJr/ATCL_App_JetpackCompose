package com.tana.airtanzaniaapp.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tana.airtanzaniaapp.presentation.home.HomeUiState

@Composable
fun TripList(
    uiState: HomeUiState,
    onClick: () -> Unit,
    onViewAllClick: () -> Unit,
    modifier: Modifier,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 12.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Our Best Trips",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = if (isSystemInDarkTheme()) MaterialTheme.colors.onBackground.copy(.4f) else
                    MaterialTheme.colors.onBackground
            )
            Text(
                text = "View all",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = modifier
                    .clickable { onViewAllClick() },
                color = MaterialTheme.colors.primary
            )
        }
        Spacer(modifier = modifier.height(16.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(uiState.bestTrips) { destination ->
                TripItem(destination = destination, onClick = onClick, modifier = modifier)
            }
        }
    }
}
package com.tana.airtanzaniaapp.presentation.booking.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tana.airtanzaniaapp.R
import com.tana.airtanzaniaapp.presentation.booking.SearchFlightUiState
import com.tana.airtanzaniaapp.presentation.ui.components.ATCDatePicker
import com.tana.airtanzaniaapp.presentation.ui.components.ATCPrimaryButton
import com.tana.airtanzaniaapp.presentation.ui.components.ATCTextField
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SearchContent(
    uiState: SearchFlightUiState,
    errorMessage: String?,
    focusManager: FocusManager,
    onFromTextFieldChange: (String) -> Unit,
    onToTextFieldChange: (String) -> Unit,
    onDateScheduleChange: (LocalDate) -> Unit,
    onPassengersTextFieldChange: (String) -> Unit,
    onClassTextFieldChange: (String) -> Unit,
    onSearchFlight: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        ATCTextField(
            text = uiState.fromTextField,
            onTextChange = onFromTextFieldChange,
            label = "From",
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )
        Spacer(modifier = modifier.height(12.dp))
        ATCTextField(
            text = uiState.toTextField,
            onTextChange = onToTextFieldChange,
            label = "To",
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )
        Spacer(modifier = modifier.height(12.dp))
//        ATCTextField(
//            text = uiState.dateTextField,
//            onTextChange = onDateTextFieldChange,
//            label = "Departure",
//            keyboardOptions = KeyboardOptions(
//                capitalization = KeyboardCapitalization.Sentences,
//                imeAction = ImeAction.Next
//            ),
//            keyboardActions = KeyboardActions(
//                onNext = { focusManager.moveFocus(FocusDirection.Down) }
//            ),
//            trailingIcon = {
//                IconButton(onClick = { /*TODO*/ }) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.calender_icon),
//                        contentDescription = "Calender icon",
//                        modifier = modifier
//                            .size(24.dp),
//                        tint = MaterialTheme.colors.onBackground.copy(.3f)
//                    )
//                }
//            }
//        )
        ATCDatePicker(
            value = uiState.scheduledDate,
            onDateChange = onDateScheduleChange
        )
        Spacer(modifier = modifier.height(12.dp))
        ATCTextField(
            text = uiState.passengersTextField,
            onTextChange = onPassengersTextFieldChange,
            label = "Passengers",
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.people_icon),
                    contentDescription = "Calender icon",
                    modifier = modifier
                        .size(24.dp),
                    tint = MaterialTheme.colors.onBackground.copy(.3f)
                )
            }
        )
        Spacer(modifier = modifier.height(12.dp))
        ATCTextField(
            text = uiState.classTextField,
            onTextChange = onClassTextFieldChange,
            label = "Class",
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.clearFocus() }
            )
        )
        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = modifier.height(64.dp))
        ATCPrimaryButton(
            text = "Search flight",
            onClick = onSearchFlight,
            modifier = modifier
                .fillMaxWidth()
        )
    }
}

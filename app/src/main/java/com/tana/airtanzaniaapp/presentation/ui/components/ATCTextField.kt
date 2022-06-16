package com.tana.airtanzaniaapp.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.tana.airtanzaniaapp.R
import com.tana.airtanzaniaapp.presentation.ui.theme.TextFieldShape

@Composable
fun ATCTextField(
    text: String,
    onTextChange: (String) -> Unit,
    label: String,
    errorMessage: String? = null,
    modifier: Modifier = Modifier,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {

    val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        textColor = MaterialTheme.colors.onSurface
    )
    Column {
        OutlinedTextField(
            value = text,
            onValueChange = onTextChange,
            label = {
                Text(text = label)
            },
            isError = (errorMessage != null),
            modifier = modifier
                .fillMaxWidth()
                .heightIn(dimensionResource(id = R.dimen.text_field_height)),
            trailingIcon = trailingIcon,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            shape = TextFieldShape,
            colors = textFieldColors
        )
        if (errorMessage != null) {
            Spacer(modifier = modifier.height(8.dp))
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                modifier = modifier
                    .padding(start = 16.dp)
            )
        }
    }
}
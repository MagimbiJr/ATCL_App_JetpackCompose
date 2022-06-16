package com.tana.airtanzaniaapp.presentation.authentication.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tana.airtanzaniaapp.R
import com.tana.airtanzaniaapp.presentation.ui.components.ATCPrimaryButton
import com.tana.airtanzaniaapp.presentation.ui.components.ATCTextField
import com.tana.airtanzaniaapp.presentation.ui.theme.GreenBlue

@Composable
fun LoginContent(
    uiState: LoginUiState,
    onEmailChanged: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onRecoverPasswordClick: () -> Unit,
    focusManager: FocusManager,
    scrollState: ScrollState,
    modifier: Modifier = Modifier
) {
    if (uiState.loading) {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(12.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Center
        ) {
            AppLogo()
            Spacer(modifier = modifier.height(24.dp))
            LoginInfo(modifier = modifier)
            Spacer(modifier = modifier.height(16.dp))
            LoginInputs(
                uiState = uiState,
                onEmailChanged = onEmailChanged,
                onPasswordChange = onPasswordChange,
                onRecoverPasswordClick = onRecoverPasswordClick,
                focusManager = focusManager,
                modifier = modifier
            )
            Spacer(modifier = modifier.height(24.dp))
            LoginButtons(
                onLoginClick = onLoginClick,
                onRegisterClick = onRegisterClick,
                modifier = modifier
            )
        }
    }
}

@Composable
private fun LoginButtons(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    modifier: Modifier
) {
    Column {
        ATCPrimaryButton(
            text = "Login",
            onClick = onLoginClick,
            modifier = modifier.fillMaxWidth()
        )
        Spacer(modifier = modifier.height(12.dp))
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Don't have an Account?")
            Spacer(modifier = modifier.width(4.dp))
            Text(
                text = "Register",
                color = GreenBlue,
                modifier = modifier
                    .clickable { onRegisterClick() },
                style = MaterialTheme.typography.button,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
private fun LoginInputs(
    uiState: LoginUiState,
    onEmailChanged: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRecoverPasswordClick: () -> Unit,
    focusManager: FocusManager,
    modifier: Modifier
) {
    Column {
        ATCTextField(
            text = uiState.credentials.email,
            onTextChange = onEmailChanged,
            label = "Email",
            errorMessage = uiState.emailErrorMessage,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
        )
        Spacer(modifier = modifier.height(12.dp))
        ATCTextField(
            text = uiState.credentials.password,
            onTextChange = onPasswordChange,
            label = "Password",
            errorMessage = uiState.emailErrorMessage,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            )
        )
        Spacer(modifier = modifier.height(8.dp))
        Text(
            text = "Forgot password?",
            style = MaterialTheme.typography.button,
            fontSize = 16.sp,
            modifier = modifier
                .align(Alignment.End)
                .clickable { onRecoverPasswordClick() }
        )

        if (uiState.errorMessage != null) {
            Spacer(modifier = modifier.height(12.dp))
            Text(
                text = uiState.errorMessage,
                color = MaterialTheme.colors.error,
                modifier = modifier
                    .align(CenterHorizontally),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun LoginInfo(modifier: Modifier) {
    Text(
        text = "Login", fontSize = 35.sp,
        fontWeight = FontWeight.Bold,
    )
    Spacer(modifier = modifier.height(12.dp))
    Text(
        text = "Please Login to continue using our App",
        color = MaterialTheme.colors.onBackground.copy(.6f)
    )
}

@Composable
private fun AppLogo() {
    Image(
        painter = painterResource(id = R.drawable.ic_atcl_logo),
        contentDescription = "Logo"
    )
}

//@Preview(name = "Day", uiMode = Configuration.UI_MODE_NIGHT_NO)
//@Preview(name = "Night", uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//fun ContentScreenPreview() {
//    AirTanzaniaAppTheme {
//        val focusManager = LocalFocusManager.current
//        Surface {
//            LoginContent(
//                uiState = LoginUiState(
//                    credentials = LoginCredentials(password = "123456",)
//                ),
//                onEmailChanged = {},
//                onPasswordChange = {},
//                onRegisterClick = { /*TODO*/ },
//                onLoginClick = {},
//                onRecoverPasswordClick = {},
//                focusManager = focusManager
//            )
//        }
//    }
//}
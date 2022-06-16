package com.tana.airtanzaniaapp.presentation.authentication.register

import android.content.res.Configuration
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tana.airtanzaniaapp.R
import com.tana.airtanzaniaapp.presentation.ui.components.ATCPrimaryButton
import com.tana.airtanzaniaapp.presentation.ui.components.ATCTextButton
import com.tana.airtanzaniaapp.presentation.ui.components.ATCTextField
import com.tana.airtanzaniaapp.presentation.ui.theme.AirTanzaniaAppTheme
import com.tana.airtanzaniaapp.presentation.ui.theme.Cultured
import com.tana.airtanzaniaapp.presentation.ui.theme.GreenBlue

@Composable
fun RegisterContent(
    uiState: RegisterUiState,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onVerifyPasswordChange: (String) -> Unit,
    onChecked: (Boolean) -> Unit,
    onRegisterBtnClick: () -> Unit,
    onLoginClick: () -> Unit,
    onPrivacyPolicyClick: () -> Unit,
    focusManager: FocusManager,
    scrollState: ScrollState,
    modifier: Modifier = Modifier
) {
    if (uiState.loading) {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
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
            RegisterInfo(modifier)
            Spacer(modifier = modifier.height(16.dp))
            RegisterInputs(
                uiState = uiState,
                checked = uiState.checked,
                onFirstNameChange = onFirstNameChange,
                onLastNameChange = onLastNameChange,
                onEmailChange = onEmailChange,
                onPasswordChange = onPasswordChange,
                onVerifyPasswordChange = onVerifyPasswordChange,
                onChecked = onChecked,
                onPrivacyPolicyClick = onPrivacyPolicyClick,
                focusManager = focusManager,
                modifier = modifier
            )
            Spacer(modifier = modifier.height(24.dp))
            RegisterButtons(
                onRegisterClick = onRegisterBtnClick,
                onLoginClick = onLoginClick,
                modifier = modifier
            )
        }
    }
}

@Composable
private fun RegisterButtons(
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit,
    modifier: Modifier
) {
    Column {
        ATCPrimaryButton(
            text = "register",
            onClick = onRegisterClick,
            modifier = modifier.fillMaxWidth()
        )
        Spacer(modifier = modifier.height(12.dp))
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Already have an Account?")
            Spacer(modifier = modifier.width(4.dp))
            Text(
                text = "Log In",
                color = GreenBlue,
                modifier = modifier
                    .clickable { onLoginClick() },
                style = MaterialTheme.typography.button,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
private fun RegisterInputs(
    uiState: RegisterUiState,
    checked: Boolean,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onVerifyPasswordChange: (String) -> Unit,
    onChecked: (Boolean) -> Unit,
    onPrivacyPolicyClick: () -> Unit,
    focusManager: FocusManager,
    modifier: Modifier
) {
    Column {
        ATCTextField(
            text = uiState.credentials.firstName,
            onTextChange = onFirstNameChange,
            label = "First name",
            errorMessage = uiState.firstNameErrorMessage,
            modifier = modifier,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
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
            text = uiState.credentials.lastName,
            onTextChange = onLastNameChange,
            label = "Last name",
            errorMessage = uiState.lastNameErrorMessage,
            modifier = modifier,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
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
            text = uiState.credentials.email,
            onTextChange = onEmailChange,
            label = "Email",
            errorMessage = uiState.emailErrorMessage,
            modifier = modifier,
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
            errorMessage = uiState.passwordErrorMessage,
            modifier = modifier,
            visualTransformation = PasswordVisualTransformation(),
            trailingIcon = {},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
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
            text = uiState.credentials.verifyPassword,
            onTextChange = onVerifyPasswordChange,
            label = "Verify password",
            errorMessage = uiState.verifyPasswordErrorMessage,
            modifier = modifier,
            visualTransformation = PasswordVisualTransformation(),
            trailingIcon = {},
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
        Spacer(modifier = modifier.height(12.dp))
        Column {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val checkBoxColors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colors.primary
                )
                Checkbox(
                    checked = checked,
                    onCheckedChange = onChecked,
                    colors = checkBoxColors
                )
                Spacer(modifier = modifier.width(8.dp))
                Text(text = "I agree with the")
                Spacer(modifier = modifier.width(4.dp))
                Text(
                    text = "Privacy Policy",
                    color = GreenBlue,
                    modifier = modifier
                        .clickable { onPrivacyPolicyClick() },
                    style = MaterialTheme.typography.button,
                    fontSize = 16.sp
                )
            }
            if (uiState.checkedErrorMessage != null) {
                Spacer(modifier = modifier.height(4.dp))
                Text(
                    text = uiState.checkedErrorMessage,
                    color = MaterialTheme.colors.error,
                    modifier = modifier
                        .padding(start = 16.dp)
                )
            }

            if (uiState.errorMessage != null) {
                Spacer(modifier = modifier.height(12.dp))
                Text(
                    text = uiState.errorMessage,
                    color = MaterialTheme.colors.error,
                    modifier = modifier
                        .align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun RegisterInfo(modifier: Modifier) {
    Text(
        text = "Register", fontSize = 35.sp,
        fontWeight = FontWeight.Bold,
    )
    Spacer(modifier = modifier.height(12.dp))
    Text(
        text = "Please Register with email and sign up to continue using our App.",
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
//            RegisterContent(
//                uiState = RegisterUiState(
//                    credentials = RegCredentials(password = "123456", verifyPassword = "123456")
//                ),
//                onFirstNameChange = {},
//                onLastNameChange = {},
//                onEmailChange = {},
//                onPasswordChange = {},
//                onVerifyPasswordChange = {},
//                onChecked = {},
//                onRegisterBtnClick = { /*TODO*/ },
//                onLoginClick = {},
//                onPrivacyPolicyClick = {},
//                focusManager = focusManager
//            )
//        }
//    }
//}
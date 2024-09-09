package com.vortex.soft.sellproductsnew.presentation.login

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.vortex.soft.sellproductsnew.R
import com.vortex.soft.sellproductsnew.domain.dto.signin.SigninDto
import com.vortex.soft.sellproductsnew.navigation.Navigation
import com.vortex.soft.sellproductsnew.validation.ComposeValidation
import com.vortex.soft.sellproductsnew.validation.rules.common.NotEmptyRule
import com.vortex.soft.sellproductsnew.validation.withValidateCompose
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(navController: NavHostController, viewModel: LoginViewModel = koinViewModel()) {

    val context = LocalContext.current

    val loginUiState = viewModel.loginUiState.collectAsStateWithLifecycle()

    val nameField = remember { ComposeValidation().add(NotEmptyRule(getErrorNoEmptyField(context, "Name"))) }
    val passwordField = remember { ComposeValidation().add(NotEmptyRule(getErrorNoEmptyField(context, "Password"))) }

    val passwordVisible = rememberSaveable { mutableStateOf(false) }

    when(loginUiState.value){
        LoginUiState.Loading -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.page_loading),
                    modifier = Modifier
                        .padding(8.dp), textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelMedium
                )

                CircularProgressIndicator(
                    strokeWidth = 4.dp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(8.dp)
                )
            }
        }
        is LoginUiState.Success -> {
            viewModel.setCurrentUserId("327")
            navController.navigate(Navigation.Dashboard.destination)
        }
        is LoginUiState.Error,
        LoginUiState.Input -> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(24.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    horizontalArrangement = Arrangement.Start) {
                    Box(
                        modifier = Modifier
                            .width(7.dp)
                            .fillMaxHeight()
                            .background(colorResource(R.color.color_header))
                    )
                    Spacer(modifier = Modifier.width(18.dp))
                    Text(
                        text = stringResource(R.string.login_header),
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(align = Alignment.CenterVertically)
                            .align(Alignment.CenterVertically)
                    )

                }

                Spacer(modifier = Modifier.height(94.dp))

                TextField(
                    value = nameField.value,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    onValueChange = { nameField.value = it },
                    isError = nameField.hasError(),
                    modifier = Modifier.fillMaxWidth(),
                    supportingText = {
                        if (nameField.hasError()) {
                            TextFieldError(textError = nameField.errorMessage ?: "")
                        }
                    },
                    placeholder = {
                        Text(stringResource(R.string.login_hint_name),
                            style = MaterialTheme.typography.bodyMedium)
                    },
                    trailingIcon = {
                        if (nameField.hasError())
                            Icon(Icons.Filled.Info,"error", tint = MaterialTheme.colorScheme.error)
                    },
                    colors = TextFieldDefaults.colors().copy(
                        focusedContainerColor = Color.White,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        unfocusedContainerColor = Color.White,
                        errorTextColor = Color.Black,
                        errorContainerColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = passwordField.value,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    onValueChange = { passwordField.value = it },
                    isError = passwordField.hasError(),
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    supportingText = {
                        if (passwordField.hasError()) {
                            TextFieldError(textError = passwordField.errorMessage ?: "")
                        }
                    },
                    placeholder = {
                        Text(stringResource(R.string.login_hint_password),
                            style = MaterialTheme.typography.bodyMedium)
                    },
                    trailingIcon = {
                        PasswordVisibilityToggleIcon(
                            showPassword = passwordVisible.value,
                            onTogglePasswordVisibility = { passwordVisible.value = !passwordVisible.value })
                    },
                    colors = TextFieldDefaults.colors().copy(
                        focusedContainerColor = Color.White,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        unfocusedContainerColor = Color.White,
                        errorTextColor = Color.Black,
                        errorContainerColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(39.dp))

                Button(onClick = {
                    withValidateCompose(listOf(nameField, passwordField), {
                        //Toast.makeText(context, "All fields are valid", Toast.LENGTH_SHORT).show()
                        viewModel.login(SigninDto(nameField.value, passwordField.value))
                    })
                },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                    shape = RoundedCornerShape(35.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)) {
                    Text(
                        text = stringResource(R.string.login_button_login),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
                if(loginUiState.value is LoginUiState.Error) {
                    val error = (loginUiState.value as? LoginUiState.Error)?.failure?.toString() ?: ""
                    TextFieldError(textError = error)
                }
            }
        }
    }
}

@Composable
fun TextFieldError(textError: String) {
    Text(
        text = textError,
        modifier = Modifier.fillMaxWidth(),
        style = LocalTextStyle.current.copy(color = MaterialTheme.colorScheme.error)
    )
}

@Composable
fun PasswordVisibilityToggleIcon(
    showPassword: Boolean,
    onTogglePasswordVisibility: () -> Unit
) {
    // Determine the icon based on password visibility
    val image = if (showPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
    val contentDescription = if (showPassword) "Hide password icon" else "Show password icon"

    // IconButton to toggle password visibility
    IconButton(onClick = onTogglePasswordVisibility) {
        Icon(imageVector = image, contentDescription = contentDescription)
    }
}

fun getErrorNoEmptyField(context: Context, fieldName: String): String {
    return String.format(context.getString(R.string.validate_empty_field), fieldName)
}
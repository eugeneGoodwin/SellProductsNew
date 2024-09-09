package com.vortex.soft.sellproductsnew.presentation.enroll

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.vortex.soft.sellproductsnew.R
import com.vortex.soft.sellproductsnew.domain.dto.register.RegisterDto
import com.vortex.soft.sellproductsnew.navigation.Navigation
import com.vortex.soft.sellproductsnew.presentation.login.PasswordVisibilityToggleIcon
import com.vortex.soft.sellproductsnew.presentation.login.TextFieldError
import com.vortex.soft.sellproductsnew.validation.ComposeValidation
import com.vortex.soft.sellproductsnew.validation.rules.common.CurrentEqualRule
import com.vortex.soft.sellproductsnew.validation.rules.common.NotEmptyRule
import com.vortex.soft.sellproductsnew.validation.rules.regex.EmailRule
import com.vortex.soft.sellproductsnew.validation.validations.PasswordValidation
import com.vortex.soft.sellproductsnew.validation.withValidateCompose
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegistrationScreen(navController: NavHostController, viewModel: RegistrationViewModel = koinViewModel()) {
    val context = LocalContext.current

    val registrationUiState = viewModel.registerUiState.collectAsStateWithLifecycle()

    val name = stringResource(R.string.enroll_register_name)
    val email = stringResource(R.string.enroll_register_email)
    val password = stringResource(R.string.enroll_register_password)
    val confirmPassword = stringResource(R.string.enroll_register_confirm_password)
    val confirmEqualText = stringResource(R.string.enroll_register_confirm_no_match)

    val nameRegisterField = remember { ComposeValidation().add(NotEmptyRule(getErrorNoEmptyField(context, name))) }
    val emailRegisterField = remember { ComposeValidation().add(NotEmptyRule(getErrorNoEmptyField(context, email))).add(EmailRule(getErrorNoFormatField(context, email))) }
    val passwordField = remember { PasswordValidation(password) }
    val passwordConfirmField = remember { ComposeValidation()
        .add(NotEmptyRule(getErrorNoEmptyField(context, confirmPassword)))
        .add(
            CurrentEqualRule({ passwordField.value }, confirmEqualText)
        )}

    val passwordVisible = rememberSaveable { mutableStateOf(false) }
    val passwordConfirmVisible = rememberSaveable { mutableStateOf(false) }

    when(registrationUiState.value){
        RegistrationUiState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize()
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
        is RegistrationUiState.Success -> {
            viewModel.setCurrentUserId("327")
            navController.navigate(Navigation.Dashboard.destination)
        }
        is RegistrationUiState.Error,
        is RegistrationUiState.Input -> {
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
                        text = stringResource(R.string.enroll_header),
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(align = Alignment.CenterVertically)
                            .align(Alignment.CenterVertically)
                    )

                }

                Spacer(modifier = Modifier.height(52.dp))

                TextField(
                    value = nameRegisterField.value,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    onValueChange = { nameRegisterField.value = it },
                    isError = nameRegisterField.hasError(),
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(stringResource(R.string.enroll_register_name),
                            style = MaterialTheme.typography.bodyMedium)
                    },
                    supportingText = {
                        if (nameRegisterField.hasError()) {
                            TextFieldError(textError = nameRegisterField.errorMessage ?: "")
                        }
                    },
                    trailingIcon = {
                        if (nameRegisterField.hasError())
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
                    value = emailRegisterField.value,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    onValueChange = { emailRegisterField.value = it },
                    isError = emailRegisterField.hasError(),
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(stringResource(R.string.enroll_register_email),
                            style = MaterialTheme.typography.bodyMedium)
                    },
                    supportingText = {
                        if (emailRegisterField.hasError()) {
                            TextFieldError(textError = emailRegisterField.errorMessage ?: "")
                        }
                    },
                    trailingIcon = {
                        if (emailRegisterField.hasError())
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
                    onValueChange = {
                        passwordField.value = it
                        withValidateCompose(listOf(passwordField), {})
                    },
                    isError = passwordField.hasError(),
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    placeholder = {
                        Text(stringResource(R.string.enroll_register_password),
                            style = MaterialTheme.typography.bodyMedium)
                    },
                    supportingText = {
                        if (passwordField.hasError()) {
                            TextFieldError(textError = passwordField.errorMessage)
                        }
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

                Spacer(modifier = Modifier.height(20.dp))

                Column() {
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(
                            text = stringResource(R.string.validate_min_symbols),
                            textDecoration = if(passwordField.hasMinLength) TextDecoration.LineThrough else TextDecoration.None,
                            modifier = Modifier.align(Alignment.CenterVertically),
                            style = MaterialTheme.typography.bodyMedium)
                        Text(
                            text = stringResource(R.string.validate_big_letters),
                            textDecoration = if(passwordField.hasUpperCase) TextDecoration.LineThrough else TextDecoration.None,
                            modifier = Modifier.align(Alignment.CenterVertically),
                            style = MaterialTheme.typography.bodyMedium)

                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(
                            text = stringResource(R.string.validate_small_letters),
                            textDecoration = if(passwordField.hasLowerCase) TextDecoration.LineThrough else TextDecoration.None,
                            modifier = Modifier.align(Alignment.CenterVertically),
                            style = MaterialTheme.typography.bodyMedium)
                        Text(
                            text = stringResource(R.string.validate_digits),
                            textDecoration = if(passwordField.hasDigit) TextDecoration.LineThrough else TextDecoration.None,
                            modifier = Modifier.align(Alignment.CenterVertically),
                            style = MaterialTheme.typography.bodyMedium)

                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                TextField(
                    value = passwordConfirmField.value,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    onValueChange = { passwordConfirmField.value = it },
                    isError = passwordConfirmField.hasError(),
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = if (passwordConfirmVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    placeholder = {
                        Text(text = stringResource(R.string.enroll_register_confirm_password),
                            style = MaterialTheme.typography.bodyMedium)
                    },
                    supportingText = {
                        if (passwordConfirmField.hasError()) {
                            TextFieldError(textError = passwordConfirmField.errorMessage ?: "")
                        }
                    },
                    trailingIcon = {
                        PasswordVisibilityToggleIcon(
                            showPassword = passwordConfirmVisible.value,
                            onTogglePasswordVisibility = { passwordConfirmVisible.value = !passwordConfirmVisible.value })
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
                    withValidateCompose(listOf(nameRegisterField, emailRegisterField, passwordField, passwordConfirmField), {
                        Toast.makeText(context, "All fields are valid", Toast.LENGTH_SHORT).show()
                        val registerDto = RegisterDto(nameRegisterField.value,
                            emailRegisterField.value,
                            passwordField.value,
                            passwordConfirmField.value)
                        viewModel.register(registerDto)
                    })
                },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                    shape = RoundedCornerShape(35.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)) {
                    Text(
                        text = stringResource(R.string.enroll_register),
                        style = MaterialTheme.typography.labelLarge)
                }
                if(registrationUiState.value is RegistrationUiState.Error) {
                    val error = (registrationUiState.value as? RegistrationUiState.Error)?.failure?.toString() ?: ""
                    TextFieldError(textError = error)
                }
            }
        }
    }
}

fun getErrorNoEmptyField(context: Context, fieldName: String): String {
    return String.format(context.getString(R.string.validate_empty_field), fieldName)
}

fun getErrorNoFormatField(context: Context, fieldName: String): String {
    return String.format(context.getString(R.string.validate_no_format_field), fieldName)
}
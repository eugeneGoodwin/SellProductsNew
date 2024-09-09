package com.vortex.soft.sellproductsnew.presentation.enroll

import androidx.lifecycle.ViewModel
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType
import com.vortex.soft.sellproductsnew.domain.dto.register.RegisterDto
import com.vortex.soft.sellproductsnew.domain.dto.register.RegisterResponseDto
import com.vortex.soft.sellproductsnew.domain.interactor.usecases.register.RegisterUseCase
import com.vortex.soft.sellproductsnew.domain.interactor.usecases.user.SetCurrentUserIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class RegistrationViewModel (val registerUseCase: RegisterUseCase,
                             val setCurrentUserIdUseCase: SetCurrentUserIdUseCase
) : ViewModel() {

    private val _registerUiState = MutableStateFlow<RegistrationUiState>(RegistrationUiState.Input)
    val registerUiState get() = _registerUiState.asStateFlow()

    fun register(registerDto: RegisterDto) {
        handleLoading()
        registerUseCase(registerDto) { it.eitherHandle(::handleFailure, ::handleRegisterResponse) }
    }

    private fun handleLoading() {
        _registerUiState.value = RegistrationUiState.Loading
    }

    private fun handleRegisterResponse(response: RegisterResponseDto) {
        _registerUiState.value = RegistrationUiState.Success(response.token.isNotEmpty())
    }

    private fun handleFailure(failure: FailureType) {
        _registerUiState.value = RegistrationUiState.Error(failure)
    }

    fun setCurrentUserId(userId: String) {
        setCurrentUserIdUseCase(userId)
    }
}

sealed interface RegistrationUiState{
    data object Loading : RegistrationUiState
    data object Input : RegistrationUiState
    data class Success(val response: Boolean): RegistrationUiState
    data class Error(val failure: FailureType) : RegistrationUiState
}
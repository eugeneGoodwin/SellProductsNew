package com.vortex.soft.sellproductsnew.presentation.login

import androidx.lifecycle.ViewModel
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType
import com.vortex.soft.sellproductsnew.domain.dto.signin.SigninDto
import com.vortex.soft.sellproductsnew.domain.dto.signin.SigninResponseDto
import com.vortex.soft.sellproductsnew.domain.interactor.usecases.signin.SigninUseCase
import com.vortex.soft.sellproductsnew.domain.interactor.usecases.user.SetCurrentUserIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class LoginViewModel(val signinUseCase: SigninUseCase,
                     val setCurrentUserIdUseCase: SetCurrentUserIdUseCase
) : ViewModel() {

    private val _loginUiState = MutableStateFlow<LoginUiState>(LoginUiState.Input)
    val loginUiState get() = _loginUiState.asStateFlow()

    fun login(signinDto: SigninDto) {
        handleLoading()
        signinUseCase(signinDto) { it.eitherHandle(::handleFailure, ::handleSigninResponse) }
    }

    private fun handleLoading() {
        _loginUiState.value = LoginUiState.Loading
    }

    private fun handleSigninResponse(response: SigninResponseDto) {
        _loginUiState.value = LoginUiState.Success(response)
    }

    private fun handleFailure(failure: FailureType) {
        _loginUiState.value = LoginUiState.Error(failure)
    }

    fun setCurrentUserId(userId: String) {
        setCurrentUserIdUseCase(userId)
    }
}

sealed interface LoginUiState{
    data object Loading : LoginUiState
    data object Input : LoginUiState
    data class Success(val response: SigninResponseDto): LoginUiState
    data class Error(val failure: FailureType) : LoginUiState
}
package com.vortex.soft.sellproductsnew.presentation.dashboard.profile

import androidx.lifecycle.ViewModel
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType
import com.vortex.soft.sellproductsnew.domain.dto.user.UserDto
import com.vortex.soft.sellproductsnew.domain.interactor.usecases.user.GetUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel (
        val getUserUseCase: GetUserUseCase
) : ViewModel() {
    private val _profileUiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    val profileUiState get() = _profileUiState.asStateFlow()

    fun getUser(userId: Int) {
        handleLoading()
        getUserUseCase(userId) { it.eitherHandle(::handleFailure, ::handleUser) }
    }

    private fun handleUser(userDto: UserDto) {
        _profileUiState.value = ProfileUiState.Success(userDto)
    }

    private fun handleLoading() {
        _profileUiState.value = ProfileUiState.Loading
    }

    private fun handleFailure(failure: FailureType) {
        _profileUiState.value = ProfileUiState.Error(failure)
    }
}

sealed interface ProfileUiState{
    data object Loading : ProfileUiState
    data class Success(val response: UserDto): ProfileUiState
    data class Error(val failure: FailureType) : ProfileUiState
}
package com.vortex.soft.sellproducts.mainboard.cart

import androidx.lifecycle.ViewModel
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType
import com.vortex.soft.sellproductsnew.domain.common.type.None
import com.vortex.soft.sellproductsnew.domain.dto.order.OrderDto
import com.vortex.soft.sellproductsnew.domain.interactor.usecases.cart.GetCartUseCase
import com.vortex.soft.sellproductsnew.domain.interactor.usecases.cart.IsCartExistUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CartViewModel (
    val getCartUseCase: GetCartUseCase,
    val isCartExistUseCase: IsCartExistUseCase
) : ViewModel() {
    private val _cartUiState = MutableStateFlow<CartUiState>(CartUiState.Loading)
    val cartUiState get() = _cartUiState.asStateFlow()

    fun getCart() {
        handleLoading()
        getCartUseCase(None()) { it.eitherHandle(::handleFailure, ::handleCart) }
    }

    private fun handleCart(orderDto: OrderDto) {
        _cartUiState.value = CartUiState.Success(orderDto)
    }

    fun isCartExist() = isCartExistUseCase(None()) { it.eitherHandle(::handleFailure, ::handleCartExist) }

    private fun handleCartExist(isExist: Boolean) {
        if(isExist) getCart()
        else handleEmpty()
    }

    fun checkout(orderDto: OrderDto) {}

    private fun handleLoading() {
        _cartUiState.value = CartUiState.Loading
    }

    private fun handleEmpty() {
        _cartUiState.value = CartUiState.Empty
    }

    private fun handleFailure(failure: FailureType) {
        _cartUiState.value = CartUiState.Error(failure)
    }
}

sealed interface CartUiState{
    data object Loading : CartUiState
    data object Empty : CartUiState
    data class Success(val response: OrderDto): CartUiState
    data class Error(val failure: FailureType) : CartUiState
}
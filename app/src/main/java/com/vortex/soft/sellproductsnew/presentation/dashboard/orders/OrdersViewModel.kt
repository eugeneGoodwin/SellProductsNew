package com.vortex.soft.sellproductsnew.presentation.dashboard.orders

import androidx.lifecycle.ViewModel
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType
import com.vortex.soft.sellproductsnew.domain.common.type.None
import com.vortex.soft.sellproductsnew.domain.dto.order.OrderDto
import com.vortex.soft.sellproductsnew.domain.interactor.usecases.order.GetOrdersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class OrdersViewModel(val getOrdersUseCase: GetOrdersUseCase) : ViewModel() {
    private val _ordersUiState = MutableStateFlow<OrdersUiState>(OrdersUiState.Loading)
    val ordersUiState get() = _ordersUiState.asStateFlow()

    fun getOrders() {
        handleLoading()
        getOrdersUseCase(None()) { it.eitherHandle(::handleFailure, ::handleOrders) }
    }

    private fun handleOrders(listOrderDto: List<OrderDto>) {
        _ordersUiState.value = OrdersUiState.Success(listOrderDto)
    }

    private fun handleLoading() {
        _ordersUiState.value = OrdersUiState.Loading
    }

    private fun handleFailure(failure: FailureType) {
        _ordersUiState.value = OrdersUiState.Error(failure)
    }
}

sealed interface OrdersUiState{
    data object Loading : OrdersUiState
    data class Success(val response: List<OrderDto>): OrdersUiState
    data class Error(val failure: FailureType) : OrdersUiState
}
package com.vortex.soft.sellproducts.mainboard.catalog

import androidx.lifecycle.ViewModel
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType
import com.vortex.soft.sellproductsnew.domain.common.type.None
import com.vortex.soft.sellproductsnew.domain.dto.order.OrderDto
import com.vortex.soft.sellproductsnew.domain.dto.order.OrderItemDto
import com.vortex.soft.sellproductsnew.domain.dto.product.ProductDto
import com.vortex.soft.sellproductsnew.domain.entity.order.OrderEntity
import com.vortex.soft.sellproductsnew.domain.interactor.usecases.cart.AddOrderItemToCartUseCase
import com.vortex.soft.sellproductsnew.domain.interactor.usecases.cart.CreateCartUseCase
import com.vortex.soft.sellproductsnew.domain.interactor.usecases.cart.IsCartExistUseCase
import com.vortex.soft.sellproductsnew.domain.interactor.usecases.product.GetProductsUseCase
import com.vortex.soft.sellproductsnew.domain.interactor.usecases.user.GetCurrentUserIdUseCase
import com.vortex.soft.sellproductsnew.utils.DateTimeUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CatalogViewModel(
    val getProductsUseCase: GetProductsUseCase,
    val createCartUseCase: CreateCartUseCase,
    val isCartExistUseCase: IsCartExistUseCase,
    val currentUserIdUseCase: GetCurrentUserIdUseCase,
    val addOrderItemToCartUseCase: AddOrderItemToCartUseCase
) : ViewModel() {
    var orderItem: OrderItemDto? = null
    private val _catalogUiState = MutableStateFlow<CatalogUiState>(CatalogUiState.Loading)
    val catalogUiState get() = _catalogUiState.asStateFlow()

    fun getProducts() {
        handleLoading()
        getProductsUseCase(None()) { it.eitherHandle(::handleFailure, ::handleProducts) }
    }

    private fun handleProducts(listProductDto: List<ProductDto>) {
        _catalogUiState.value = CatalogUiState.Success(listProductDto)
    }

    fun addToCart(orderItemDto: OrderItemDto) {
        orderItem = orderItemDto
        isCartExistUseCase(None()) { it.eitherHandle(::handleFailure, ::handleCartExist) }
    }

    private fun handleLoading() {
        _catalogUiState.value = CatalogUiState.Loading
    }

    private fun handleFailure(failure: FailureType) {
        _catalogUiState.value = CatalogUiState.Error(failure)
    }

    private fun handleCartExist(isExist: Boolean) {
        if(!isExist) {
            val currentUserId = currentUserIdUseCase(None())
            currentUserId.toIntOrNull()?.let {
                createCartUseCase(initCart(it)) { it.eitherHandle(::handleFailure, ::handleCreateCart) }
            } ?: run { handleFailure(FailureType.CurrentUserError) }
        } else {
            orderItem?.let { addOrderItemToCartUseCase(it) { it.eitherHandle(::handleFailure, ::handleAddOrderItem) } }
        }
    }

    private fun handleCreateCart(isCreated: Boolean) {
        if(isCreated) orderItem?.let { addOrderItemToCartUseCase(it) { it.eitherHandle(::handleFailure, ::handleAddOrderItem) } }
    }

    private fun handleAddOrderItem(isAdded: Boolean) {}

    fun initCart(currentUserId: Int): OrderDto {
        return OrderEntity(currentUserId, "", DateTimeUtil.getCurrentDateString(), "new").toDTO()
    }
}

sealed interface CatalogUiState{
    data object Loading : CatalogUiState
    data class Success(val response: List<ProductDto>): CatalogUiState
    data class Error(val failure: FailureType) : CatalogUiState
}
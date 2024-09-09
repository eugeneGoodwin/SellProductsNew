package com.vortex.soft.sellproductsnew.domain.interactor.usecases.cart

import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType
import com.vortex.soft.sellproductsnew.domain.dto.order.OrderDto
import com.vortex.soft.sellproductsnew.domain.dto.order.OrderItemDto
import com.vortex.soft.sellproductsnew.domain.interactor.base.Interactor
import com.vortex.soft.sellproductsnew.domain.repository.CartRepository

class AddOrderItemToCartUseCase (private val repository: CartRepository
) : Interactor<OrderItemDto, Boolean>() {

    override suspend fun run(params: OrderItemDto): Either<FailureType, Boolean> {
        return repository.addOrderItemToCart(params)
    }
}
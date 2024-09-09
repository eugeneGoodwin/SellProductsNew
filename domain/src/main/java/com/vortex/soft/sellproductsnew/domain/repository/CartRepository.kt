package com.vortex.soft.sellproductsnew.domain.repository

import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType
import com.vortex.soft.sellproductsnew.domain.dto.order.OrderDto
import com.vortex.soft.sellproductsnew.domain.dto.order.OrderItemDto

interface CartRepository {
    fun createCart(order: OrderDto): Either<FailureType, Boolean>
    fun addOrderItemToCart(orderItem: OrderItemDto): Either<FailureType, Boolean>
    fun getCart(): Either<FailureType, OrderDto>
    //fun setCart(order: OrderDto): Either<FailureType, Boolean>

    fun isCartExist(): Either<FailureType, Boolean>
}
package com.vortex.soft.sellproductsnew.domain.repository

import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType
import com.vortex.soft.sellproductsnew.domain.dto.order.OrderDto


interface OrderRepository {
    suspend fun sendOrder(order: OrderDto): Either<FailureType, Boolean>
    suspend fun getOrders(): Either<FailureType, List<OrderDto>>
}
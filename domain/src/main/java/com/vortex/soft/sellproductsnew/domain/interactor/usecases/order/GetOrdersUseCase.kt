package com.vortex.soft.sellproductsnew.domain.interactor.usecases.order

import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType
import com.vortex.soft.sellproductsnew.domain.common.type.None
import com.vortex.soft.sellproductsnew.domain.dto.order.OrderDto
import com.vortex.soft.sellproductsnew.domain.interactor.base.Interactor
import com.vortex.soft.sellproductsnew.domain.repository.OrderRepository

class GetOrdersUseCase (private val repository: OrderRepository
) : Interactor<None, List<OrderDto>>() {

    override suspend fun run(params: None): Either<FailureType, List<OrderDto>> {
        return repository.getOrders()
    }
}
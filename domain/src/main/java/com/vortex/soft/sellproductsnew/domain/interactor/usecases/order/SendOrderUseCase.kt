package com.vortex.soft.sellproductsnew.domain.interactor.usecases.order

import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType
import com.vortex.soft.sellproductsnew.domain.dto.order.OrderDto
import com.vortex.soft.sellproductsnew.domain.interactor.base.Interactor
import com.vortex.soft.sellproductsnew.domain.repository.OrderRepository

class SendOrderUseCase(private val repository: OrderRepository
) : Interactor<OrderDto, Boolean>() {

    override suspend fun run(params: OrderDto): Either<FailureType, Boolean> {
        return repository.sendOrder(params)
    }
}
package com.vortex.soft.sellproductsnew.data.repository.order.source

import com.vortex.soft.sellproductsnew.data.source.remote.dto.StatusJsonDto
import com.vortex.soft.sellproductsnew.data.source.remote.dto.order.OrderJsonDto
import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType

interface OrderRemote {
    suspend fun sendOrder(token: String, order: OrderJsonDto): Either<FailureType, StatusJsonDto>
    suspend fun getOrders(token: String): Either<FailureType, List<OrderJsonDto>>
}
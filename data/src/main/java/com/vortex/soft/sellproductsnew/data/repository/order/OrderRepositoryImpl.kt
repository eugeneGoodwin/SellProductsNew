package com.vortex.soft.sellproductsnew.data.repository.order

import com.vortex.soft.sellproductsnew.data.mapper.order.OrderMapper
import com.vortex.soft.sellproductsnew.data.repository.common.PreferencesProvider
import com.vortex.soft.sellproductsnew.data.repository.order.source.OrderRemote
import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.monad.flatMap
import com.vortex.soft.sellproductsnew.domain.common.monad.map
import com.vortex.soft.sellproductsnew.domain.common.monad.suspendFlatMap
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType
import com.vortex.soft.sellproductsnew.domain.dto.order.OrderDto
import com.vortex.soft.sellproductsnew.domain.repository.OrderRepository

class OrderRepositoryImpl (
        private val remote: OrderRemote,
        private val prefProvider: PreferencesProvider,
        private val mapper: OrderMapper
): OrderRepository {

    override suspend fun getOrders(): Either<FailureType, List<OrderDto>> {
        return prefProvider.getToken().suspendFlatMap { token ->
            remote.getOrders(token).map { it.map { mapper.mapJsonToDomain(it)} }
        }
    }

    override suspend fun sendOrder(order: OrderDto): Either<FailureType, Boolean> {
        return prefProvider.getToken().suspendFlatMap { token ->
            remote.sendOrder(token, mapper.mapDomainToJson(order)).map { if(it.status == "success") true else false }
        }
    }
}
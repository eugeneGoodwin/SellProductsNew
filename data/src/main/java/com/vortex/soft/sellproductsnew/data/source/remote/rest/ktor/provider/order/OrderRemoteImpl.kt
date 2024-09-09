package com.vortex.soft.sellproductsnew.data.source.remote.rest.ktor.provider.order

import com.vortex.soft.sellproductsnew.data.repository.order.source.OrderRemote
import com.vortex.soft.sellproductsnew.data.source.remote.dto.StatusJsonDto
import com.vortex.soft.sellproductsnew.data.source.remote.dto.order.OrderJsonDto
import com.vortex.soft.sellproductsnew.data.source.remote.rest.ktor.adapter.RestCallAdapter
import com.vortex.soft.sellproductsnew.data.source.remote.rest.ktor.adapter.api_get_orders
import com.vortex.soft.sellproductsnew.data.source.remote.rest.ktor.adapter.api_order
import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType

class OrderRemoteImpl (
        private val restAdapter: RestCallAdapter
) : OrderRemote {

    override suspend fun getOrders(token: String): Either<FailureType, List<OrderJsonDto>> {
        return restAdapter.make<List<OrderJsonDto>, List<OrderJsonDto>>(
            { api_get_orders(token) }, { it })
    }

    override suspend fun sendOrder(token: String, order: OrderJsonDto): Either<FailureType, StatusJsonDto> {
        return restAdapter.make<StatusJsonDto, StatusJsonDto>(
            { api_order(token, order) }, { it })
    }
}
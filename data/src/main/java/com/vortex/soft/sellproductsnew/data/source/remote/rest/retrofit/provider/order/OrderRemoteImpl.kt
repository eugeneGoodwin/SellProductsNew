package com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.provider.order

import com.vortex.soft.sellproductsnew.data.repository.order.source.OrderRemote
import com.vortex.soft.sellproductsnew.data.source.remote.dto.StatusJsonDto
import com.vortex.soft.sellproductsnew.data.source.remote.dto.order.OrderJsonDto
import com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.common.RestAdapter
import com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.common.SellProductsAPI
import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType

class OrderRemoteImpl (
    private val apiService: SellProductsAPI,
    private val restAdapter: RestAdapter
) : OrderRemote {

    override suspend fun getOrders(token: String): Either<FailureType, List<OrderJsonDto>> {
        return restAdapter.make(apiService.getOrders(token), { it })
    }

    override suspend fun sendOrder(token: String, order: OrderJsonDto): Either<FailureType, StatusJsonDto> {
        return restAdapter.make(apiService.sendOrder(token, order), { it })
    }
}
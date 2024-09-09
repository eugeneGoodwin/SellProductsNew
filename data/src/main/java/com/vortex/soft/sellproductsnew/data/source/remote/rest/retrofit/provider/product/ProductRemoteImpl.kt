package com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.provider.product

import com.vortex.soft.sellproductsnew.data.repository.product.source.ProductRemote
import com.vortex.soft.sellproductsnew.data.source.remote.dto.product.ProductJsonDto
import com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.common.RestAdapter
import com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.common.SellProductsAPI
import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType

class ProductRemoteImpl (
    private val apiService: SellProductsAPI,
    private val restAdapter: RestAdapter
) : ProductRemote {

    override suspend fun getProducts(token: String): Either<FailureType, List<ProductJsonDto>> {
        return restAdapter.make(apiService.getProducts(token), { it })
    }
}
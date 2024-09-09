package com.vortex.soft.sellproductsnew.data.source.remote.rest.ktor.provider.product

import com.vortex.soft.sellproductsnew.data.repository.product.source.ProductRemote
import com.vortex.soft.sellproductsnew.data.source.remote.dto.product.ProductJsonDto
import com.vortex.soft.sellproductsnew.data.source.remote.dto.register.RegisterResponseJsonDto
import com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.common.RestAdapter
import com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.common.SellProductsAPI
import com.vortex.soft.sellproductsnew.data.source.remote.rest.ktor.adapter.RestCallAdapter
import com.vortex.soft.sellproductsnew.data.source.remote.rest.ktor.adapter.api_get_products
import com.vortex.soft.sellproductsnew.data.source.remote.rest.ktor.adapter.api_register
import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType

class ProductRemoteImpl (
    private val restAdapter: RestCallAdapter
) : ProductRemote {

    override suspend fun getProducts(token: String): Either<FailureType, List<ProductJsonDto>> {
        return restAdapter.make<List<ProductJsonDto>, List<ProductJsonDto>>(
            { api_get_products(token) }, { it })
    }
}
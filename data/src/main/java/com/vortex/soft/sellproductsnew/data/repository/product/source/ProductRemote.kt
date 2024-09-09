package com.vortex.soft.sellproductsnew.data.repository.product.source

import com.vortex.soft.sellproductsnew.data.source.remote.dto.product.ProductJsonDto
import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType

interface ProductRemote {
    suspend fun getProducts(token: String): Either<FailureType, List<ProductJsonDto>>
}
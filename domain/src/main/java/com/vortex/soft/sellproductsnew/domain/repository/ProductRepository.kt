package com.vortex.soft.sellproductsnew.domain.repository

import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType
import com.vortex.soft.sellproductsnew.domain.dto.product.ProductDto

interface ProductRepository {
    suspend fun getProducts(): Either<FailureType, List<ProductDto>>
}
package com.vortex.soft.sellproductsnew.data.repository.product

import com.vortex.soft.sellproductsnew.data.mapper.product.ProductMapper
import com.vortex.soft.sellproductsnew.data.repository.common.PreferencesProvider
import com.vortex.soft.sellproductsnew.data.repository.product.source.ProductRemote
import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.monad.flatMap
import com.vortex.soft.sellproductsnew.domain.common.monad.map
import com.vortex.soft.sellproductsnew.domain.common.monad.suspendFlatMap
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType
import com.vortex.soft.sellproductsnew.domain.dto.product.ProductDto
import com.vortex.soft.sellproductsnew.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val remote: ProductRemote,
    private val prefProvider: PreferencesProvider,
    private val mapper: ProductMapper
): ProductRepository {

    override suspend fun getProducts(): Either<FailureType, List<ProductDto>> {
        return prefProvider.getToken().suspendFlatMap { token ->
            remote.getProducts(token).map { it.map { mapper.mapJsonToDomain(it)} }
        }
    }
}
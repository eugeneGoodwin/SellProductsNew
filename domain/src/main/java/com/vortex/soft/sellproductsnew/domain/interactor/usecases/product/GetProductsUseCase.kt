package com.vortex.soft.sellproductsnew.domain.interactor.usecases.product

import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType
import com.vortex.soft.sellproductsnew.domain.common.type.None
import com.vortex.soft.sellproductsnew.domain.dto.product.ProductDto
import com.vortex.soft.sellproductsnew.domain.interactor.base.Interactor
import com.vortex.soft.sellproductsnew.domain.repository.ProductRepository

class GetProductsUseCase( private val repository: ProductRepository
) : Interactor<None, List<ProductDto>>() {

    override suspend fun run(params: None): Either<FailureType, List<ProductDto>> {
        return repository.getProducts()
    }
}
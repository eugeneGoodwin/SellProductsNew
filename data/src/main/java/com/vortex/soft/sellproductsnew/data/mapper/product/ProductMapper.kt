package com.vortex.soft.sellproductsnew.data.mapper.product

import com.vortex.soft.sellproductsnew.data.mapper.common.JsonMapper
import com.vortex.soft.sellproductsnew.data.source.remote.dto.product.ProductJsonDto
import com.vortex.soft.sellproductsnew.domain.dto.product.ProductDto

class ProductMapper: JsonMapper<ProductDto, ProductJsonDto> {
    override fun mapDomainToJson(type: ProductDto): ProductJsonDto {
        return ProductJsonDto(
            type.id,
            type.title,
            type.description,
            type.price,
            type.oldPrice,
            type.imageUrl,
            type.categoryId,
            type.brandId,
            type.brandName,
            type.groupId,
            type.status,
            type.state
        )
    }
    override fun mapJsonToDomain(type: ProductJsonDto): ProductDto {
        return ProductDto(
            type.id,
            type.title,
            type.description,
            type.price,
            type.oldPrice,
            type.imageUrl,
            type.categoryId,
            type.brandId,
            type.brandName,
            type.groupId,
            type.status,
            type.state
        )
    }
}
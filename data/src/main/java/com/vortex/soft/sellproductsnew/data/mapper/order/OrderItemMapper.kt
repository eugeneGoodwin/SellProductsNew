package com.vortex.soft.sellproductsnew.data.mapper.order

import com.vortex.soft.sellproductsnew.data.mapper.common.JsonMapper
import com.vortex.soft.sellproductsnew.data.source.local.database.room.DEFAULT_ROW_ID
import com.vortex.soft.sellproductsnew.data.source.local.database.room.table.OrderItemSqlEntity
import com.vortex.soft.sellproductsnew.data.source.remote.dto.order.OrderItemJsonDto
import com.vortex.soft.sellproductsnew.domain.dto.order.OrderItemDto

class OrderItemMapper : JsonMapper<OrderItemDto, OrderItemJsonDto> {
    override fun mapDomainToJson(type: OrderItemDto): OrderItemJsonDto {
        return OrderItemJsonDto(
            type.productId,
            type.productDescription,
            type.productImageUrl,
            type.quantity,
            type.price,
            type.totalPrice
        )
    }
    override fun mapJsonToDomain(type: OrderItemJsonDto): OrderItemDto {
        return OrderItemDto(
            type.productId,
            type.productDescription,
            type.productImageUrl,
            type.quantity,
            type.price,
            type.totalPrice
        )
    }
}

class OrderItemSqlMapper() {
    fun mapDomainToSql(orderId: String, type: OrderItemDto): OrderItemSqlEntity {
        return OrderItemSqlEntity(
                DEFAULT_ROW_ID,
                orderId,
                type.productId,
                type.productDescription,
                type.productImageUrl,
                type.quantity,
                type.price,
                type.totalPrice
            )
    }

    fun mapSqlToDomain(type: OrderItemSqlEntity): OrderItemDto {
        return OrderItemDto(
            type.productId,
            type.productDescription,
            type.productImageUrl,
            type.quantity,
            type.price,
            type.totalPrice
        )
    }
}
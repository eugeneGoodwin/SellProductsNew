package com.vortex.soft.sellproductsnew.data.mapper.order

import com.vortex.soft.sellproductsnew.data.mapper.common.ComplexSqlMapper
import com.vortex.soft.sellproductsnew.data.mapper.common.JsonMapper
import com.vortex.soft.sellproductsnew.data.source.local.database.room.DEFAULT_ROW_ID
import com.vortex.soft.sellproductsnew.data.source.local.database.room.table.OrderItemSqlEntity
import com.vortex.soft.sellproductsnew.data.source.local.database.room.table.OrderSqlEntity
import com.vortex.soft.sellproductsnew.data.source.remote.dto.order.OrderJsonDto
import com.vortex.soft.sellproductsnew.domain.dto.order.OrderDto
import com.vortex.soft.sellproductsnew.domain.dto.order.OrderItemDto

class OrderMapper(val itemMapper: OrderItemMapper): JsonMapper<OrderDto, OrderJsonDto> {
    override fun mapDomainToJson(type: OrderDto): OrderJsonDto {
        return OrderJsonDto(
                type.id,
                type.userId,
                type.description,
                type.totalPrice,
                type.orderDate,
                type.listOrderItems.map { itemMapper.mapDomainToJson(it) },
                type.status
        )
    }
    override fun mapJsonToDomain(type: OrderJsonDto): OrderDto {
        return OrderDto(
                type.id,
                type.userId,
                type.description,
                type.totalPrice,
                type.orderDate,
                type.listOrderItems.map { itemMapper.mapJsonToDomain(it) },
                type.status
        )
    }
}

class ComplexOrderSqlMapper(): ComplexSqlMapper<OrderDto, OrderSqlEntity, OrderItemSqlEntity> {
    override fun mapDomainToSql(type: OrderDto): Pair<OrderSqlEntity, List<OrderItemSqlEntity>> {
        return Pair(OrderSqlEntity(
            DEFAULT_ROW_ID,
            type.id,
            type.userId,
            type.description,
            type.totalPrice,
            type.orderDate,
            type.status
        ), type.listOrderItems.map {
            OrderItemSqlEntity(
                DEFAULT_ROW_ID,
                type.id,
                it.productId,
                it.productDescription,
                it.productImageUrl,
                it.quantity,
                it.price,
                it.totalPrice
            )
        })
    }

    override fun mapSqlToDomain(type: OrderSqlEntity, type1: List<OrderItemSqlEntity>): OrderDto {
        return OrderDto(
            type.orderId,
            type.userId,
            type.description,
            type.totalPrice,
            type.orderDate,
            type1.map { OrderItemDto(it.productId, it.productDescription, it.productImageUrl, it.quantity, it.price, it.totalPrice) },
            type.status
        )
    }
}
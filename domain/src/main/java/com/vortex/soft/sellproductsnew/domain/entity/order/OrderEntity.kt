package com.vortex.soft.sellproductsnew.domain.entity.order

import com.vortex.soft.sellproductsnew.domain.dto.order.OrderDto
import com.vortex.soft.sellproductsnew.domain.dto.order.OrderItemDto
import java.util.*

class OrderEntity (val userId: Int,
                   val description: String,
                   val orderDate: String,
                   val status: String,
                   val id: String = generateId()) {
    val totalPrice: String
        get() = (listOrderItems.fold(0){ acc, next -> acc + next.totalPrice.toInt()}).toString()
    var listOrderItems: MutableList<OrderItemEntity> = mutableListOf()

    constructor(dto: OrderDto):this(dto.userId, dto.description, dto.orderDate, dto.status, dto.id) {
        listOrderItems = dto.listOrderItems.map { OrderItemEntity(it) }.toMutableList()
    }
    fun toDTO() = OrderDto(id, userId, description, totalPrice, orderDate, listOrderItems.map {OrderItemDto(it.productId, it.productDesciption, it.productImageUrl, it.quantity, it.price, it.totalPrice)}, status)
    fun addOrderItem(orderItem: OrderItemEntity) { listOrderItems.add(orderItem) }
    fun copy(userId: Int = this.userId,
             description: String = this.description,
             orderDate: String = this.orderDate,
             status: String = this.status,
             id: String = this.id,
             items: MutableList<OrderItemEntity> = this.listOrderItems) = OrderEntity(userId, description, orderDate, status, id).apply { listOrderItems.addAll(items) }

    companion object {
        fun generateId() = UUID.randomUUID().toString()
    }
}
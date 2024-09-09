package com.vortex.soft.sellproductsnew.domain.dto.order

data class OrderDto(
        val id: String,
        val userId: Int,
        val description: String,
        val totalPrice: String,
        val orderDate: String,
        val listOrderItems: List<OrderItemDto>,
        val status: String // new, pending_accept, accept, decline, delivery, delivered
)
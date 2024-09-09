package com.vortex.soft.sellproductsnew.domain.dto.order

data class OrderItemDto (
    val productId: Int,
    val productDescription: String,
    val productImageUrl: String,
    val quantity: Int,
    val price: String,
    val totalPrice: String
)
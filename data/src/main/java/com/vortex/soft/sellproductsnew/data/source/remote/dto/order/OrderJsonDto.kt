package com.vortex.soft.sellproductsnew.data.source.remote.dto.order

import com.google.gson.annotations.SerializedName
import com.vortex.soft.sellproductsnew.domain.dto.order.OrderItemDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderJsonDto (
        @SerialName("id")
        val id: String,
        @SerialName("user_id")
        val userId: Int,
        @SerialName("description")
        val description: String,
        @SerialName("total_price")
        val totalPrice: String,
        @SerialName("order_date")
        val orderDate: String,
        @SerialName("order_items")
        val listOrderItems: List<OrderItemJsonDto>,
        @SerialName("status")
        val status: String
)


/*
data class OrderJsonDto (
        @SerializedName("id")
        val id: String,
        @SerializedName("user_id")
        val userId: Int,
        @SerializedName("description")
        val description: String,
        @SerializedName("total_price")
        val totalPrice: String,
        @SerializedName("order_date")
        val orderDate: String,
        @SerializedName("order_items")
        val listOrderItems: List<OrderItemJsonDto>,
        @SerializedName("status")
        val status: String
)*/

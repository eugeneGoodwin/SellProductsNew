package com.vortex.soft.sellproductsnew.data.source.remote.dto.order

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class OrderItemJsonDto (
        @SerialName("product_id")
        val productId: Int,
        @SerialName("product_description")
        val productDescription: String,
        @SerialName("product_image_url")
        val productImageUrl: String,
        @SerialName("quantity")
        val quantity: Int,
        @SerialName("price")
        val price: String,
        @SerialName("total_price")
        val totalPrice: String
)



/*
data class OrderItemJsonDto (
        @SerializedName("product_id")
        val productId: Int,
        @SerializedName("product_description")
        val productDescription: String,
        @SerializedName("product_image_url")
        val productImageUrl: String,
        @SerializedName("quantity")
        val quantity: Int,
        @SerializedName("price")
        val price: String,
        @SerializedName("total_price")
        val totalPrice: String
)*/

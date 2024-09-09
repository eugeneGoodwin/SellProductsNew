package com.vortex.soft.sellproductsnew.data.source.remote.dto.product

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductJsonDto (
    @SerialName("id")
    val id: String,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("price")
    val price: String,
    @SerialName("old_price")
    val oldPrice: String,
    @SerialName("img_url")
    val imageUrl: String,
    @SerialName("category_id")
    val categoryId: String,
    @SerialName("brand_id")
    val brandId: String,
    @SerialName("brand_name")
    val brandName: String,
    @SerialName("group_id")
    val groupId: String,
    @SerialName("status")
    val status: String,
    @SerialName("state")
    val state: String
)



/*
data class ProductJsonDto (
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("old_price")
    val oldPrice: String,
    @SerializedName("img_url")
    val imageUrl: String,
    @SerializedName("category_id")
    val categoryId: String,
    @SerializedName("brand_id")
    val brandId: String,
    @SerializedName("brand_name")
    val brandName: String,
    @SerializedName("group_id")
    val groupId: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("state")
    val state: String
)*/

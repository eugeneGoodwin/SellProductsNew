package com.vortex.soft.sellproductsnew.domain.dto.product

data class ProductDto(
        val id: String,
        val title: String,
        val description: String,
        val price: String,
        val oldPrice: String,
        val imageUrl: String,
        val categoryId: String,
        val brandId: String,
        val brandName: String,
        val groupId: String,
        val status: String, // available, not available,
        val state: String //new
)
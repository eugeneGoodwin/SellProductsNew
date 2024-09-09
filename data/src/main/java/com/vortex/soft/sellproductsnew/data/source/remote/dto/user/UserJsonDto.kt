package com.vortex.soft.sellproductsnew.data.source.remote.dto.user

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserJsonDto (
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String,
        @SerialName("surname")
        val surname: String,
        @SerialName("email")
        val email: String,
        @SerialName("location")
        val location: String,
        @SerialName("phone")
        val phone: String,
        @SerialName("img_url")
        val imageUrl: String
)




/*
data class UserJsonDto (
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("surname")
        val surname: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("location")
        val location: String,
        @SerializedName("phone")
        val phone: String,
        @SerializedName("img_url")
        val imageUrl: String
)*/

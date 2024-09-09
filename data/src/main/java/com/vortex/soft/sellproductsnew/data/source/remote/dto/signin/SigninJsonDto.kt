package com.vortex.soft.sellproductsnew.data.source.remote.dto.signin

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SigninJsonDto(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String
)


/*
data class SigninJsonDto(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String
)*/

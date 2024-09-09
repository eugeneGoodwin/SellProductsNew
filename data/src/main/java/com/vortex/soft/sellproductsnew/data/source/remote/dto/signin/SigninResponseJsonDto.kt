package com.vortex.soft.sellproductsnew.data.source.remote.dto.signin

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SigninResponseJsonDto (
    @SerialName("token")
    val token: String,
    @SerialName("refresh_token")
    val refreshToken: String
)


/*
data class SigninResponseJsonDto (
    @SerializedName("token")
    val token: String,
    @SerializedName("refresh_token")
    val refreshToken: String
)*/

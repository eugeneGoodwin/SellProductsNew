package com.vortex.soft.sellproductsnew.data.source.remote.dto.register

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class RegisterResponseJsonDto (
    @SerialName("token")
    val token: String,
    @SerialName("refresh_token")
    val refreshToken: String
)


/*
class RegisterResponseJsonDto (
    @SerializedName("token")
    val token: String,
    @SerializedName("refresh_token")
    val refreshToken: String
)*/

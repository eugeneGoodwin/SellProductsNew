package com.vortex.soft.sellproductsnew.data.source.remote.dto.register

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class RegisterJsonDto (
    @SerialName("username")
    val username: String,
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String,
    @SerialName("confirm_password")
    val passwordConfirm: String
)


/*
class RegisterJsonDto (
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("confirm_password")
    val passwordConfirm: String
)*/

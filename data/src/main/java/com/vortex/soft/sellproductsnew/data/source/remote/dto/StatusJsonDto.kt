package com.vortex.soft.sellproductsnew.data.source.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatusJsonDto(
    @SerialName("status")
    val status: String
)


/*
data class StatusJsonDto(
    @SerializedName("status")
    val status: String
)*/

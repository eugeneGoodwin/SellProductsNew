package com.vortex.soft.sellproductsnew.data.source.remote.rest.ktor.adapter

import com.vn.cashberrymp.AppConstant
import io.ktor.client.*
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.takeFrom
import kotlinx.serialization.json.Json
import io.ktor.serialization.kotlinx.json.json

val httpClient = HttpClient(Android) {
    install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }

    defaultRequest {
        url {
                takeFrom(AppConstant.BASE_URL)
                //parameters.append("api-key", BuildKonfig.API_KEY)
        }
    }
}

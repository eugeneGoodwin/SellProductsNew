package com.vortex.soft.sellproductsnew.data.di.module

import com.vn.cashberrymp.AppConstant
import com.vortex.soft.sellproductsnew.data.source.remote.rest.ktor.mock.MockSellProductsEngine
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single {
        /*HttpClient(Android) {
            install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }

            defaultRequest {
                url {
                    takeFrom(AppConstant.BASE_URL)
                }
            }
        }*/
        HttpClient(MockSellProductsEngine().get()) {
            install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }

            defaultRequest {
                url {
                    takeFrom(AppConstant.BASE_URL)
                }
            }
        }
    }
}
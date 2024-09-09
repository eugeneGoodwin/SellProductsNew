package com.vortex.soft.sellproductsnew.data.source.remote.rest.ktor.adapter

import com.vortex.soft.sellproductsnew.data.source.remote.dto.order.OrderJsonDto
import com.vortex.soft.sellproductsnew.data.source.remote.dto.register.RegisterJsonDto
import com.vortex.soft.sellproductsnew.data.source.remote.dto.signin.SigninJsonDto
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.http.encodedPath

fun HttpRequestBuilder.api_login(dto: SigninJsonDto) {
    url {
        encodedPath = "/api/login"
    }
    contentType(ContentType.Application.Json)
    setBody(dto)
    method = HttpMethod.Post
}

fun HttpRequestBuilder.api_register(dto: RegisterJsonDto) {
    url {
        encodedPath = "/api/register"
    }
    contentType(ContentType.Application.Json)
    setBody(dto)
    method = HttpMethod.Post
}

fun HttpRequestBuilder.api_get_products(token: String) {
    header(HttpHeaders.Authorization, token)
    url {
        encodedPath = "/api/get-products"
    }
    contentType(ContentType.Application.Json)
    method = HttpMethod.Get
}

fun HttpRequestBuilder.api_get_orders(token: String) {
    header(HttpHeaders.Authorization, token)
    url {
        encodedPath = "/api/get-orders"
    }
    contentType(ContentType.Application.Json)
    method = HttpMethod.Get
}

fun HttpRequestBuilder.api_order(token: String, dto: OrderJsonDto) {
    header(HttpHeaders.Authorization, token)
    url {
        encodedPath = "/api/order"
    }
    contentType(ContentType.Application.Json)
    setBody(dto)
    method = HttpMethod.Post
}

fun HttpRequestBuilder.api_user(token: String, dto: Int) {
    header(HttpHeaders.Authorization, token)
    url {
        encodedPath = "/api/user"
    }
    contentType(ContentType.Application.Json)
    setBody(dto)
    method = HttpMethod.Post
}
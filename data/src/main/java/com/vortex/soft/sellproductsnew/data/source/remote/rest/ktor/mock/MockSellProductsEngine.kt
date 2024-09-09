package com.vortex.soft.sellproductsnew.data.source.remote.rest.ktor.mock

import com.vortex.soft.sellproductsnew.data.source.remote.rest.ktor.mock.mock_responses.LoginMockResponse
import com.vortex.soft.sellproductsnew.data.source.remote.rest.ktor.mock.mock_responses.OrderMockResponse
import com.vortex.soft.sellproductsnew.data.source.remote.rest.ktor.mock.mock_responses.OrdersMockResponse
import com.vortex.soft.sellproductsnew.data.source.remote.rest.ktor.mock.mock_responses.ProductsMockResponse
import com.vortex.soft.sellproductsnew.data.source.remote.rest.ktor.mock.mock_responses.RegisterMockResponse
import com.vortex.soft.sellproductsnew.data.source.remote.rest.ktor.mock.mock_responses.UserMockResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf

class MockSellProductsEngine {
    fun get() = client.engine

    private val responseHeaders = headersOf("Content-Type" to listOf(ContentType.Application.Json.toString()))
    private val client = HttpClient(MockEngine) {
        engine {
            addHandler { request ->
                if (request.url.encodedPath == "/api/get-products") {
                    respond(ProductsMockResponse(), HttpStatusCode.OK, responseHeaders)
                } else if (request.url.encodedPath == "/api/login") {
                    respond(LoginMockResponse(), HttpStatusCode.OK, responseHeaders)
                } else if (request.url.encodedPath == "/api/register") {
                    respond(RegisterMockResponse(), HttpStatusCode.OK, responseHeaders)
                } else if (request.url.encodedPath == "/api/get-orders") {
                    respond(OrdersMockResponse(), HttpStatusCode.OK, responseHeaders)
                } else if (request.url.encodedPath == "/api/order") {
                    respond(OrderMockResponse(), HttpStatusCode.OK, responseHeaders)
                } else if (request.url.encodedPath == "/api/user") {
                    respond(UserMockResponse(), HttpStatusCode.OK, responseHeaders)
                } else {
                    error("Unhandled ${request.url.encodedPath}")
                }
            }
        }
    }
}
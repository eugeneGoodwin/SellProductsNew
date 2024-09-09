package com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.mock_provider.interceptor

interface MockRequest {
    fun isMatch(requestType: String, url: String, requestBodyJson: String): Boolean
    fun response(): MockResponse
}
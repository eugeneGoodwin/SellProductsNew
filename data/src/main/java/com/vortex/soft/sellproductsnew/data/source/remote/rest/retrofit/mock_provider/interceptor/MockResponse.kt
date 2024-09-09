package com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.mock_provider.interceptor

interface MockResponse {
    fun statusCode() : Int
    fun fileResId() : Int
}
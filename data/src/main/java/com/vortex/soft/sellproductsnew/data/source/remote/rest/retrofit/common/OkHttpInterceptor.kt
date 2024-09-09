package com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.common

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.util.*

class OkHttpInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestWithAndroidHeaders = addHeaderFields(request)
        val response = chain.proceed(requestWithAndroidHeaders)
        return response
    }

    private fun addHeaderFields(request: Request): Request = request.newBuilder()
        .addHeader("Content-Type", "application/json")
        .addHeader("User-Agent", "Android OS")
        .addHeader("client", "mob-app-android")
        .addHeader("language", Locale.getDefault().language ?: "none")
        //.addHeader("app-version", android.os.Build.VERSION.RELEASE ?: "none")
        //.addHeader("app-version", configure.getBuildVersion() ?: "none")
        .build()
}
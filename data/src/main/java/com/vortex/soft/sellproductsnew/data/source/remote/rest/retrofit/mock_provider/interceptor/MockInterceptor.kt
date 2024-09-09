package com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.mock_provider.interceptor

import android.content.res.Resources
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.IOException
import okio.Buffer


class MockInterceptor(private val res: Resources, listOfMocks: List<MockRequest> = emptyList()) : Interceptor {

    private val mockMatcher = MockMatcher(listOfMocks)

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val mockRequest = routeToMock(originalRequest)

        return if (mockRequest == null) {
            chain.proceed(originalRequest)
        } else {
            val mockResponse = mockRequest.response()
            val readBytes = res.openRawResource(mockResponse.fileResId()).readBytes()

            return chain.proceed(chain.request()).newBuilder()
            //return Response.Builder()
                    .code(mockResponse.statusCode())
                    .protocol(Protocol.HTTP_1_0)
                    .request(chain.request())
                    .body(readBytes.toResponseBody("application/json".toMediaTypeOrNull()))
                    .addHeader("content-type", "application/json")
                    .build()

        }
    }

    private fun routeToMock(originalRequest: Request): MockRequest? {
        val requestType = originalRequest.method
        val url = originalRequest.url.toString()
        val requestBodyString = originalRequest.body.asString()

        return mockMatcher.match(requestType, url, requestBodyString)
    }

    private fun RequestBody?.asString() : String {
        this ?: return ""

        return try {
            val buffer = Buffer()
            this.writeTo(buffer)

            buffer.readUtf8()
        } catch (e: IOException) {
            ""
        }
    }

}
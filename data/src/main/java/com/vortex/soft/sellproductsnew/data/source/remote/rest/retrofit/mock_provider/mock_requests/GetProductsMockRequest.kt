package com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.mock_provider.mock_requests

import com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.mock_provider.interceptor.MockRequest
import com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.mock_provider.interceptor.MockResponse
import com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.mock_provider.mock_requests.responses.GetProductsMockResponse
import com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.mock_provider.mock_requests.responses.SigninMockResponse

class GetProductsMockRequest: MockRequest {
    override fun isMatch(requestType: String, url: String, requestBodyJson: String): Boolean {
        return requestType.toLowerCase() == "get" && url.contains("api/get-products")
    }

    override fun response(): MockResponse = GetProductsMockResponse()
}
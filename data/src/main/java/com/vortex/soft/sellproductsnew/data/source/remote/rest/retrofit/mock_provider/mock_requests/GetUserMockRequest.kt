package com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.mock_provider.mock_requests

import com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.mock_provider.interceptor.MockRequest
import com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.mock_provider.interceptor.MockResponse
import com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.mock_provider.mock_requests.responses.GetUserMockResponse

class GetUserMockRequest : MockRequest {
    override fun isMatch(requestType: String, url: String, requestBodyJson: String): Boolean {
        return requestType.toLowerCase() == "post" && url.contains("api/user")
    }

    override fun response(): MockResponse = GetUserMockResponse()
}
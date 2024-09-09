package com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.mock_provider.mock_requests

import com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.mock_provider.interceptor.MockRequest
import com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.mock_provider.interceptor.MockResponse
import com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.mock_provider.mock_requests.responses.SendOrderMockResponse
import com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.mock_provider.mock_requests.responses.SigninMockResponse

class SendOrderMockRequest : MockRequest {
    override fun isMatch(requestType: String, url: String, requestBodyJson: String): Boolean {
        return requestType.toLowerCase() == "post" && url.contains("api/order")
    }

    override fun response(): MockResponse = SendOrderMockResponse()
}
package com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.mock_provider.mock_requests.responses

import com.vortex.soft.sellproductsnew.data.R
import com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.mock_provider.interceptor.MockResponse

class GetProductsMockResponse: MockResponse {
    override fun fileResId(): Int = R.raw.get_products_mock_response
    override fun statusCode(): Int = 200
}
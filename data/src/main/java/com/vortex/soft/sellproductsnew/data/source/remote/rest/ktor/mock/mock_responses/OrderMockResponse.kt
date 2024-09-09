package com.vortex.soft.sellproductsnew.data.source.remote.rest.ktor.mock.mock_responses

object OrderMockResponse {
    operator fun invoke(): String = """
    {
        "status": "success"
    }
    """
}
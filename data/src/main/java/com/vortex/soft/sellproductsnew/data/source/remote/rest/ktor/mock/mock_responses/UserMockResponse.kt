package com.vortex.soft.sellproductsnew.data.source.remote.rest.ktor.mock.mock_responses

object UserMockResponse {
    operator fun invoke(): String = """
    {
        "id":1,
        "name":"Test",
        "surname":"Testov",
        "email":"tst@gmail.com",
        "location":"Ukraine, Kiev",
        "phone":"555-55-55",
        "img_url":"https://www.pngall.com/wp-content/uploads/8/Lenovo-PNG-Free-Download.png"
    }
    """
}
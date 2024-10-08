package com.vortex.soft.sellproductsnew.data.source.remote.rest.ktor.mock.mock_responses

object OrdersMockResponse {
    operator fun invoke(): String = """
    [
      {
            "id":"1",
            "user_id":5,
            "description":"Purchase",
            "total_price":"130000",
            "order_date":"21.12.2024",
            "order_items":[
              {
                "product_id": 1,
                "product_description": "MSI GL63 8RC",
                "product_image_url": "https://www.pngall.com/wp-content/uploads/8/Lenovo-PNG-Free-Download.png",
                "quantity": 3,
                "price": "25000",
                "total_price": "75000"
              },
              {
                "product_id": 3,
                "product_description": "ASUS TUF Gaming FX505GD",
                "product_image_url": "https://www.pngall.com/wp-content/uploads/8/Lenovo-Transparent.png",
                "quantity": 1,
                "price": "55000",
                "total_price": "55000"
              }
            ],
            "status":"delivered"
          },
          {
            "id":"2",
            "user_id":437,
            "description":"Purchase",
            "total_price":"155000",
            "order_date":"01.01.2022",
            "order_items":[
              {
                "product_id": 1,
                "product_description": "MSI GL63 8RC",
                "product_image_url": "https://www.pngall.com/wp-content/uploads/8/Lenovo-PNG-Free-Download.png",
                "quantity": 1,
                "price": "100000",
                "total_price": "100000"
              },
              {
                "product_id": 5,
                "product_description": "Xiaomi RedmiBook 16 i5",
                "product_image_url":"https://www.pngall.com/wp-content/uploads/11/Grey-Dodge-Challenger-PNG-Clipart.png",
                "quantity": 1,
                "price": "55000",
                "total_price": "55000"
              }
            ],
            "status":"delivered"
          },
          {
            "id":"3",
            "user_id":5,
            "description":"Purchase",
            "total_price":"50000",
            "order_date":"10.01.2024",
            "order_items":[
              {
                "product_id": 1,
                "product_description": "MSI GL63 8RC",
                "product_image_url": "https://www.pngall.com/wp-content/uploads/8/Lenovo-PNG-Free-Download.png",
                "quantity": 2,
                "price": "25000",
                "total_price": "50000"
              }
            ],
            "status":"delivered"
      }
    ]
    """
}
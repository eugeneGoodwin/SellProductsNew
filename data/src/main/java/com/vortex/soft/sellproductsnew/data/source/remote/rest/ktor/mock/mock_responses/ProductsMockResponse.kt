package com.vortex.soft.sellproductsnew.data.source.remote.rest.ktor.mock.mock_responses

object ProductsMockResponse {
    operator fun invoke(): String = """
    [
          {
            "id":"1",
            "title":"Notebook MSI",
            "description":"MSI GL63 8RC",
            "price":"38000",
            "old_price":"41000",
            "img_url":"https://www.pngall.com/wp-content/uploads/8/Lenovo-PNG-Free-Download.png",
            "category_id":"3",
            "brand_id":"5",
            "brand_name":"MSI",
            "group_id":"2",
            "status": "available",
            "state": "new"
          },
          {
            "id":"2",
            "title":"Notebook Lenovo",
            "description":"Lenovo L340-15IRH Gaming",
            "price":"23000",
            "old_price":"25000",
            "img_url":"https://www.pngall.com/wp-content/uploads/8/Lenovo-PNG.png",
            "category_id":"2",
            "brand_id":"3",
            "brand_name":"Lenovo",
            "group_id":"1",
            "status": "available",
            "state": "old"
          },
          {
            "id":"3",
            "title":"Notebook ASUS",
            "description":"ASUS TUF Gaming FX505GD",
            "price":"25000",
            "old_price":"27000",
            "img_url":"https://www.pngall.com/wp-content/uploads/8/Lenovo-Transparent.png",
            "category_id":"3",
            "brand_id":"1",
            "brand_name":"ASUS",
            "group_id":"2",
            "status": "available",
            "state": "new"
          },
          {
            "id":"4",
            "title":"Notebook ACER",
            "description":"MSI GL63 8RC",
            "price":"38000",
            "old_price":"41000",
            "img_url":"https://www.pngall.com/wp-content/uploads/8/Lenovo-Laptop-Transparent.png",
            "category_id":"3",
            "brand_id":"5",
            "brand_name":"ACER",
            "group_id":"2",
            "status": "available",
            "state": "new"
          },
          {
            "id":"5",
            "title":"Notebook XIAOMI",
            "description":"Xiaomi RedmiBook 16 i5",
            "price":"21000",
            "old_price":"22000",
            "img_url":"https://www.pngall.com/wp-content/uploads/11/Grey-Dodge-Challenger-PNG-Clipart.png",
            "category_id":"3",
            "brand_id":"7",
            "brand_name":"XIAOMI",
            "group_id":"2",
            "status": "available",
            "state": "new"
          }
    ]
    """
}
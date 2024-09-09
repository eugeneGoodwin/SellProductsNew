package com.vortex.soft.sellproductsnew.data.source.local.database.room.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vortex.soft.sellproductsnew.data.source.local.database.room.TableNames

@Entity(tableName = TableNames.TABLE_ORDER_ITEM)
data class OrderItemSqlEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "order_id")
    val orderId: String,

    @ColumnInfo(name = "product_id")
    val productId: Int,

    @ColumnInfo(name = "product_description")
    val productDescription: String,

    @ColumnInfo(name = "product_image_url")
    val productImageUrl: String,

    @ColumnInfo(name = "quantity")
    val quantity: Int,

    @ColumnInfo(name = "price")
    val price: String,

    @ColumnInfo(name = "totalPrice")
    val totalPrice: String
)
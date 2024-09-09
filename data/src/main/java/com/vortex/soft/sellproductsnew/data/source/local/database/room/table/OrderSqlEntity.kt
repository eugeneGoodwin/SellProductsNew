package com.vortex.soft.sellproductsnew.data.source.local.database.room.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vortex.soft.sellproductsnew.data.source.local.database.room.TableNames.TABLE_ORDER


@Entity(tableName = TABLE_ORDER)
data class OrderSqlEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "order_id")
    val orderId: String,

    @ColumnInfo(name = "user_id")
    val userId: Int,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "total_price")
    val totalPrice: String,

    @ColumnInfo(name = "order_date")
    val orderDate: String,

    @ColumnInfo(name = "status")
    val status: String
)
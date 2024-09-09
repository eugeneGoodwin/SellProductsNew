package com.vortex.soft.sellproductsnew.data.source.local.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vortex.soft.sellproductsnew.data.source.local.database.room.ConfigDatabase.DATABASE_VERSION
import com.vortex.soft.sellproductsnew.data.source.local.database.room.dao.OrderDao
import com.vortex.soft.sellproductsnew.data.source.local.database.room.dao.OrderItemDao
import com.vortex.soft.sellproductsnew.data.source.local.database.room.table.OrderItemSqlEntity
import com.vortex.soft.sellproductsnew.data.source.local.database.room.table.OrderSqlEntity

@Database(
    version = DATABASE_VERSION,
    entities = [
        OrderSqlEntity::class,
        OrderItemSqlEntity::class
    ],
    exportSchema = false
)
abstract class SellProductsDataBase : RoomDatabase() {
    abstract fun orderDao(): OrderDao
    abstract fun orderItemDao(): OrderItemDao
}
package com.vortex.soft.sellproductsnew.data.source.local.database.room.dao

import androidx.room.*
import com.vortex.soft.sellproductsnew.data.repository.order.source.OrderItemLocal
import com.vortex.soft.sellproductsnew.data.source.local.database.room.TableNames.TABLE_ORDER_ITEM
import com.vortex.soft.sellproductsnew.data.source.local.database.room.table.OrderItemSqlEntity

@Dao
interface OrderItemDao: OrderItemLocal {

    @Query("select * from $TABLE_ORDER_ITEM where id = :id")
    override fun getById(id: Long): OrderItemSqlEntity

    @Query("select * from $TABLE_ORDER_ITEM where order_id = :orderId")
    override fun getByOrderId(orderId: String): List<OrderItemSqlEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insert(row: OrderItemSqlEntity): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    override fun update(row: OrderItemSqlEntity): Int

    @Delete
    override fun delete(row: OrderItemSqlEntity): Int

    @Query("DELETE FROM $TABLE_ORDER_ITEM")
    override fun deleteAll()

    @Query("select * from $TABLE_ORDER_ITEM")
    fun getAllOrders(): List<OrderItemSqlEntity>
}
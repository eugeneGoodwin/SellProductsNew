package com.vortex.soft.sellproductsnew.data.source.local.database.room.dao

import androidx.room.*
import com.vortex.soft.sellproductsnew.data.repository.order.source.OrderLocal
import com.vortex.soft.sellproductsnew.data.source.local.database.room.TableNames.TABLE_ORDER
import com.vortex.soft.sellproductsnew.data.source.local.database.room.table.OrderSqlEntity

@Dao
interface OrderDao: OrderLocal {

    @Query("select * from $TABLE_ORDER where id = :id")
    override fun getById(id: Long): OrderSqlEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insert(order: OrderSqlEntity): Long

    @Update
    override fun update(order: OrderSqlEntity): Int

    @Query("UPDATE $TABLE_ORDER SET total_price = :totalPrice WHERE order_id = :orderId")
    override fun update(orderId: String, totalPrice: String): Int

    @Delete
    override fun delete(order: OrderSqlEntity): Int

    @Query("DELETE FROM $TABLE_ORDER")
    override fun deleteAll()



    @Query("select * from $TABLE_ORDER")
    fun getAllOrders(): List<OrderSqlEntity>

    @Query("select * from $TABLE_ORDER where order_id = :orderId")
    override fun getByOrderId(orderId: String) : OrderSqlEntity

    fun insertOrUpdate(item: OrderSqlEntity) {
        val itemFromDB = getById(item.id)
        if (itemFromDB != null)
            insert(item)
        else
            update(item)
    }
}
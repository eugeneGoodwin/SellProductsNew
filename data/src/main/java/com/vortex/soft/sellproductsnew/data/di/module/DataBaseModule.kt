package com.vortex.soft.sellproductsnew.data.di.module

import androidx.room.Room
import com.vortex.soft.sellproductsnew.data.repository.order.source.OrderItemLocal
import com.vortex.soft.sellproductsnew.data.repository.order.source.OrderLocal
import com.vortex.soft.sellproductsnew.data.source.local.database.room.ConfigDatabase.DB_NAME
import com.vortex.soft.sellproductsnew.data.source.local.database.room.SellProductsDataBase
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(get(), SellProductsDataBase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()//Migrate with data loss!
            //.addMigrations(Migration_1_2()) //Use migration to save data in application after schema was changed
            .build()
    }

    factory <OrderLocal> { get<SellProductsDataBase>().orderDao() }
    factory <OrderItemLocal> { get<SellProductsDataBase>().orderItemDao() }
}
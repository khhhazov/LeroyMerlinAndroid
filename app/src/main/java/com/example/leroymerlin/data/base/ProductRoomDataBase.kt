package com.example.leroymerlin.data.base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.leroymerlin.data.product.list.room.dao.ProductListDao
import com.example.leroymerlin.data.product.list.room.dao.ProductListEntity

@Database(entities = [
    ProductListEntity::class
], version = 1, exportSchema = true)
abstract class ProductRoomDataBase: RoomDatabase() {

    abstract fun productListDao(): ProductListDao
}
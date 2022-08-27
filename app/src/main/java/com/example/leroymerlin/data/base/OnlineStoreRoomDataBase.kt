package com.example.leroymerlin.data.base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.leroymerlin.data.categories.list.room.dao.CategoryListDao
import com.example.leroymerlin.data.categories.list.room.dao.CategoryListEntity
import com.example.leroymerlin.data.product.list.room.dao.ProductListDao
import com.example.leroymerlin.data.product.list.room.dao.ProductListEntity

@Database(entities = [
    ProductListEntity::class,
    CategoryListEntity::class
], version = 1, exportSchema = true)
abstract class OnlineStoreRoomDataBase: RoomDatabase() {

    abstract fun productListDao(): ProductListDao

    abstract fun categoryListDao(): CategoryListDao
}
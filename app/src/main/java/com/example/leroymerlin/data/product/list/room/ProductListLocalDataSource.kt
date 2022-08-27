package com.example.leroymerlin.data.product.list.room

import com.example.leroymerlin.data.product.list.room.dao.ProductListEntity

interface ProductListLocalDataSource {

    suspend fun loadAllProducts(): List<ProductListEntity>

    suspend fun saveAllProducts(list :List<ProductListEntity>)
}
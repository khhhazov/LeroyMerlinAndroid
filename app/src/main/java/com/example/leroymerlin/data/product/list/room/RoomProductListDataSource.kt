package com.example.leroymerlin.data.product.list.room

import com.example.leroymerlin.data.product.list.room.dao.ProductListDao
import com.example.leroymerlin.data.product.list.room.dao.ProductListEntity

class RoomProductListDataSource(private val productListDao: ProductListDao):
    ProductListLocalDataSource {
    override suspend fun loadAllProducts(): List<ProductListEntity> {
        return productListDao.loadAllProducts()
    }

    override suspend fun saveAllProducts(list: List<ProductListEntity>) {
        productListDao.saveAllProducts(list)
    }

}
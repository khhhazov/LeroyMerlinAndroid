package com.example.leroymerlin.data.product.list

import com.example.leroymerlin.data.product.list.room.ProductListLocalDataSource
import com.example.leroymerlin.data.product.list.room.dao.ProductListEntity

class ProductListRepository(
    private val productListLocalDataSource: ProductListLocalDataSource
) {
    suspend fun fetchProductList(): List<ProductListEntity> {
        return productListLocalDataSource.loadAllProducts()
    }

    suspend fun initDataBase(list: ArrayList<ProductListEntity>) {
        productListLocalDataSource.saveAllProducts(list)
    }

}
package com.example.leroymerlin.data.product.list.room.dao

import androidx.room.*

@Dao
interface ProductListDao {
    @Query("SELECT * FROM ${ProductListEntity.TABLE_NAME}")
    suspend fun loadAllProducts(): List<ProductListEntity>

    @Insert(entity = ProductListEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProduct(productEntity: ProductListEntity)

    @Insert
    @JvmSuppressWildcards
    suspend fun saveAllProducts(products: List<ProductListEntity>)

    @Delete(entity = ProductListEntity::class)
    suspend fun deleteProduct(productEntity: ProductListEntity)
}
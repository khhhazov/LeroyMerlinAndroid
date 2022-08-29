package com.example.leroymerlin.data.categories.list.room.dao

import androidx.room.*

@Dao
interface CategoryListDao {
    @Query("SELECT * FROM ${CategoryListEntity.TABLE_NAME}")
    suspend fun loadAllCategories(): List<CategoryListEntity>

    @Insert(entity = CategoryListEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCategory(categoriesEntity: CategoryListEntity)

    @Insert
    @JvmSuppressWildcards
    suspend fun saveAllCategories(categories: List<CategoryListEntity>)

    @Delete(entity = CategoryListEntity::class)
    suspend fun deleteCategory(categoriesEntity: CategoryListEntity)
}
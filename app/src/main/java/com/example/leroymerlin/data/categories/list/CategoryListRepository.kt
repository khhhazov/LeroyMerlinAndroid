package com.example.leroymerlin.data.categories.list

import com.example.leroymerlin.data.categories.list.room.CategoryListLocalDataSource
import com.example.leroymerlin.data.categories.list.room.dao.CategoryListEntity

class CategoryListRepository(
    private val categoryListLocalDataSource: CategoryListLocalDataSource
) {
    suspend fun fetchProductList(): List<CategoryListEntity> {
        return categoryListLocalDataSource.loadAllCategories()
    }

    suspend fun initDataBase(list: ArrayList<CategoryListEntity>) {
        categoryListLocalDataSource.saveAllCategories(list)
    }
}
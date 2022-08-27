package com.example.leroymerlin.data.categories.list.room

import com.example.leroymerlin.data.categories.list.room.dao.CategoryListDao
import com.example.leroymerlin.data.categories.list.room.dao.CategoryListEntity

class RoomCategoryListDataSource(private val categoryListDao: CategoryListDao):
    CategoryListLocalDataSource {
    override suspend fun loadAllCategories(): List<CategoryListEntity> {
        return categoryListDao.loadAllCategories()
    }

    override suspend fun saveAllCategories(list: List<CategoryListEntity>) {
        categoryListDao.saveAllCategories(list)
    }
}
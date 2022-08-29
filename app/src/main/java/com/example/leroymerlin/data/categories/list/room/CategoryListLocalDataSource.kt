package com.example.leroymerlin.data.categories.list.room

import com.example.leroymerlin.data.categories.list.room.dao.CategoryListEntity

interface CategoryListLocalDataSource {

    suspend fun loadAllCategories(): List<CategoryListEntity>

    suspend fun saveAllCategories(list :List<CategoryListEntity>)
}
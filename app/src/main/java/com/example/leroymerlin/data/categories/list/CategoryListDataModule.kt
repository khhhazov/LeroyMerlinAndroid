package com.example.leroymerlin.data.categories.list

import com.example.leroymerlin.data.base.OnlineStoreRoomDataBase
import com.example.leroymerlin.data.categories.list.room.CategoryListLocalDataSource
import com.example.leroymerlin.data.categories.list.room.RoomCategoryListDataSource
import com.example.leroymerlin.data.product.list.ProductListRepository
import com.example.leroymerlin.data.product.list.room.ProductListLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CategoryListDataModule {
    @Provides
    @Singleton
    fun provideLocalDataSource(roomDataBase: OnlineStoreRoomDataBase): CategoryListLocalDataSource {
        return RoomCategoryListDataSource(roomDataBase.categoryListDao())
    }

    @Provides
    @Singleton
    fun provideCategoryListRepository(local: CategoryListLocalDataSource): CategoryListRepository {
        return CategoryListRepository(local)
    }
}
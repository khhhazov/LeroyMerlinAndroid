package com.example.leroymerlin.data.product.list

import com.example.leroymerlin.data.base.ProductRoomDataBase
import com.example.leroymerlin.data.product.list.room.ProductListLocalDataSource
import com.example.leroymerlin.data.product.list.room.RoomProductListDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ProductListDataModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(roomDataBase: ProductRoomDataBase): ProductListLocalDataSource {
        return RoomProductListDataSource(roomDataBase.productListDao())
    }

    @Provides
    @Singleton
    fun provideProductListRepository(local: ProductListLocalDataSource): ProductListRepository {
        return ProductListRepository(local)
    }
}
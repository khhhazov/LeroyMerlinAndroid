package com.example.leroymerlin.data.di

import android.content.Context
import androidx.room.Room
import com.example.leroymerlin.data.base.ProductRoomDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            ProductRoomDataBase::class.java,
            "online_store"
        ).build()
}
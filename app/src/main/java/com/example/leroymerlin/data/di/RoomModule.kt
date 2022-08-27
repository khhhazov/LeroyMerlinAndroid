package com.example.leroymerlin.data.di

import android.content.Context
import androidx.room.Room
import com.example.leroymerlin.data.base.OnlineStoreRoomDataBase
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
            OnlineStoreRoomDataBase::class.java,
            "online_store"
        ).build()
}
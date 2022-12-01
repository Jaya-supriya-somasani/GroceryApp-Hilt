package com.example.flipkartgroceries.di

import android.content.Context
import androidx.room.Room
import com.example.flipkartgroceries.database.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DataBaseModule {
    @Provides
    fun providingAppDataBase(@ApplicationContext appContext: Context): AppDataBase {
        return Room.databaseBuilder(appContext, AppDataBase::class.java, "Groceries").build()
    }
}
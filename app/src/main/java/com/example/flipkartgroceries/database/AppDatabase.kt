package com.example.flipkartgroceries.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CategoryEntity::class,ProductEntity::class], version = 1)
abstract class AppDatabase :RoomDatabase(){
    abstract fun categoriesDao():CategoryDao
    abstract fun productsDao():ProductDao
}
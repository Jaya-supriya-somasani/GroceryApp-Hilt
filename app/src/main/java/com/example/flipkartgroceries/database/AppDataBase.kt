package com.example.flipkartgroceries.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CategoriesEntity::class,ProductsEntity::class], version = 1)
abstract class AppDataBase :RoomDatabase(){
    abstract fun categoriesDao():CategoriesDAO
    abstract fun productsDao():ProductsDao
}
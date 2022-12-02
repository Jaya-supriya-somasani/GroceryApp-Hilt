package com.example.flipkartgroceries.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CategoriesDAO {
    @Query("Select * from categoriesentity ")
    fun getAllCategories():List<CategoriesEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCategories(category: CategoriesEntity)
}
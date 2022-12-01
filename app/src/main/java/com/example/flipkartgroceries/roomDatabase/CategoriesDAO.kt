package com.example.flipkartgroceries.roomDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CategoriesDAO {
    @Query("Select category_image,category_name from categoriesentity")
    fun getAllCategories():List<CategoriesEntity>

    @Insert
    fun insertCategories(vararg category: CategoriesEntity)
}
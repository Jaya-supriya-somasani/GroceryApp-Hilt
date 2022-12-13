package com.example.flipkartgroceries.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface CategoryDao {
    @Query("Select * from categoryentity ")
    fun getAllCategories():List<CategoryEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCategories(category: CategoryEntity)

    @Update
    suspend fun updateCategoryData(category: CategoryEntity)

//    @Query("Select * from categoriesentity where ")
}
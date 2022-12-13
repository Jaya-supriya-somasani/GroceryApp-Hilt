package com.example.flipkartgroceries.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProductDao {
    @Query("Select * from productentity")
    fun getAllProducts():List<ProductEntity>
    @Insert
    fun insertProducts(vararg products:ProductEntity)

    @Update
    fun updateProductDetails(product:ProductEntity)

    @Query("Select * from productentity where category_name= :category_type ")
    fun getSpecificCategoryProducts(category_type:String):List<ProductEntity>
}
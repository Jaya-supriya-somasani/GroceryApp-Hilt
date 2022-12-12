package com.example.flipkartgroceries.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProductsDao {
    @Query("Select * from productsentity")
    fun getAllProducts():List<ProductsEntity>
    @Insert
    fun insertProducts(vararg products:ProductsEntity)

    @Update
    fun updateProductDetails(product:ProductsEntity)

    @Query("Select * from productsentity where category_name= :category_type ")
    fun getSpecificCategoryProducts(category_type:String):List<ProductsEntity>
}
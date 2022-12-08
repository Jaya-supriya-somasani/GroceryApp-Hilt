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
}
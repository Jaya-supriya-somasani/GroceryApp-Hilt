package com.example.flipkartgroceries.database

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface ProductsDao {
//    @Query("Select * from productsentity")
//    fun getAllProducts():List<ProductsDao>
    @Insert
    fun insertProducts(vararg products:ProductsEntity)
}
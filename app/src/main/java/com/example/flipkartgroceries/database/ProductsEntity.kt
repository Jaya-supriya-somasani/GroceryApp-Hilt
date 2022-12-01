package com.example.flipkartgroceries.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductsEntity(
    @PrimaryKey val categoryName:String,
    @ColumnInfo(name = "product_name") val productName:String?,
    @ColumnInfo(name = "product_weight") val productWeight:String?,
    @ColumnInfo(name = "product_mrp") val productMRP:Int,
    @ColumnInfo(name = "product_price") val productPrice:Int,
    @ColumnInfo(name = "description") val description:String,
    @ColumnInfo(name = "product_image") val productImage:String
)

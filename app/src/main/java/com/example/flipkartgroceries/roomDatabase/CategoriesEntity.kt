package com.example.flipkartgroceries.roomDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoriesEntity (
    @PrimaryKey val categoryId:String,
    @ColumnInfo(name = "category_image") val categoryImage:String?,
    @ColumnInfo(name = "category_name") val categoryName:String?)
package com.example.flipkartgroceries.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoriesEntity (
    @PrimaryKey(autoGenerate = true) val categoryId:Int,
    @ColumnInfo(name = "category_image") val categoryImage:String?,
    @ColumnInfo(name = "category_name") val categoryName:String?)
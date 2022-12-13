package com.example.flipkartgroceries.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val categoryId: Int,
    @ColumnInfo(name = "category_image") var categoryImage: String?,
    @ColumnInfo(name = "category_name") var categoryName: String?
) : Parcelable
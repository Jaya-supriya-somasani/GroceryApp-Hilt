package com.example.flipkartgroceries.ui.user.tabs.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flipkartgroceries.database.AppDatabase
import com.example.flipkartgroceries.database.CategoryEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val groceriesDataBase: AppDatabase) :
    ViewModel() {
    var categoryList = MutableStateFlow(listOf<CategoryEntity>())

    init {
        getAllCategoryList()
    }

    fun getAllCategoryList() {
        viewModelScope.launch(Dispatchers.IO) {
            categoryList.value =groceriesDataBase.categoriesDao().getAllCategories()
        }
    }
}
package com.example.flipkartgroceries.admin.view_categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flipkartgroceries.database.AppDataBase
import com.example.flipkartgroceries.database.CategoryEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewCategoriesViewModel @Inject constructor(private val groceries: AppDataBase) :
    ViewModel() {
    var list= MutableStateFlow(listOf<CategoryEntity>())

    init {
        getCategoriesData()
    }
    fun getCategoriesData() {
        viewModelScope.launch(Dispatchers.IO) {
            list.value = groceries.categoriesDao().getAllCategories()
        }
    }
}
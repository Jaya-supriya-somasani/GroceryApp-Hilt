package com.example.flipkartgroceries.ui.admin.manage_services.category.view_category

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
class ViewCategoryViewModel @Inject constructor(private val groceries: AppDatabase) :
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
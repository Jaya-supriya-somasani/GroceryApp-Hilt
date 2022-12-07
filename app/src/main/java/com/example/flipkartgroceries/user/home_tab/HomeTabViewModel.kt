package com.example.flipkartgroceries.user.home_tab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flipkartgroceries.database.AppDataBase
import com.example.flipkartgroceries.database.CategoriesEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeTabViewModel @Inject constructor(private val groceriesDataBase: AppDataBase) :
    ViewModel() {
    var categoriesDetailsList = MutableStateFlow(listOf<CategoriesEntity>())

    init {
        getAllCategoriesDetails()
    }

    private fun getAllCategoriesDetails() {
       viewModelScope.launch (Dispatchers.IO){
           categoriesDetailsList.value = groceriesDataBase.categoriesDao().getAllCategories()
       }

    }
}
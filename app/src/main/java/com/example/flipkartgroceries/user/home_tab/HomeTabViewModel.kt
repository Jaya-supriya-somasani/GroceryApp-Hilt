package com.example.flipkartgroceries.user.home_tab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flipkartgroceries.database.AppDataBase
import com.example.flipkartgroceries.database.CategoriesEntity
import com.example.flipkartgroceries.database.ProductsEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeTabViewModel @Inject constructor(private val groceriesDataBase: AppDataBase) :
    ViewModel() {
    var categoriesDetailsList = MutableStateFlow(listOf<CategoriesEntity>())
    var frequentlyBoughtProductsList = MutableStateFlow(listOf<ProductsEntity>())
    var categoriesList = MutableStateFlow(listOf<CategoriesEntity>())
    val quickBtnEventChannel=Channel<Unit>()
    val quickBtnEvent=quickBtnEventChannel.receiveAsFlow()
    init {
        getAllCategoriesDetails()
        getFrequentlyBoughtProducts()
        getCategoriesList()
    }

    private fun getAllCategoriesDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            categoriesDetailsList.value = groceriesDataBase.categoriesDao().getAllCategories()
        }
    }

    private fun getFrequentlyBoughtProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            frequentlyBoughtProductsList.value = groceriesDataBase.productsDao().getAllProducts()
        }
    }
    private fun getCategoriesList() {
        viewModelScope.launch(Dispatchers.IO) {
            categoriesList.value = groceriesDataBase.categoriesDao().getAllCategories()
        }
    }
    fun quickTryBtnClicked(){
        quickBtnEventChannel.trySend(Unit)
    }
}
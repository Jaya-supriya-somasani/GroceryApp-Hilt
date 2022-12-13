package com.example.flipkartgroceries.user.tabs.local_favourite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flipkartgroceries.database.AppDataBase
import com.example.flipkartgroceries.database.ProductEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocalFavouriteViewModel @Inject constructor(private val groceriesDataBase: AppDataBase) :
    ViewModel() {
    var frequentlyBoughtList = MutableStateFlow(listOf<ProductEntity>())
    var specificProduct = MutableStateFlow(listOf<ProductEntity>())
    init {
        getAllProductDetails()
        specificProducts()
    }

    fun getAllProductDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            frequentlyBoughtList.value = groceriesDataBase.productsDao().getAllProducts()
        }
    }

    fun specificProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                specificProduct.value =
                    groceriesDataBase.productsDao().getSpecificCategoryProducts("Vegetables")
                Log.d("TAG", "category data : ${specificProduct.value}")
            } catch (exception: Exception) {
                Log.d("TAG", "Error raised while retrieving products")
            }
        }
    }

}
package com.example.flipkartgroceries.viewProducts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flipkartgroceries.database.AppDataBase
import com.example.flipkartgroceries.database.ProductsEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewProductsViewModel @Inject constructor(private val groceries: AppDataBase) : ViewModel() {
    var productsList=MutableStateFlow(listOf<ProductsEntity>())
    init {
        getProducts()
    }
    private fun getProducts(){
        viewModelScope.launch(Dispatchers.IO) {
            productsList.value=groceries.productsDao().getAllProducts()
        }
    }
}
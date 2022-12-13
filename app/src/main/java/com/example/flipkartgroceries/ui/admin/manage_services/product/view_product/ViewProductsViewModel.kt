package com.example.flipkartgroceries.ui.admin.manage_services.product.view_product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flipkartgroceries.database.AppDatabase
import com.example.flipkartgroceries.database.ProductEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewProductsViewModel @Inject constructor(private val groceries: AppDatabase) : ViewModel() {
    var productsList=MutableStateFlow(listOf<ProductEntity>())
    init {
        getProducts()
    }
    private fun getProducts(){
        viewModelScope.launch(Dispatchers.IO) {
            productsList.value=groceries.productsDao().getAllProducts()
        }
    }
}
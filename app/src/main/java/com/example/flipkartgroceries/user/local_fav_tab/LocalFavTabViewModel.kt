package com.example.flipkartgroceries.user.local_fav_tab

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
class LocalFavTabViewModel @Inject constructor(private val groceriesDataBase: AppDataBase) :
    ViewModel() {
        var frequentlyBoughtList=MutableStateFlow(listOf<ProductsEntity>())
    init {
        getAllProductDetails()
    }
    fun getAllProductDetails(){
        viewModelScope.launch(Dispatchers.IO) {
            frequentlyBoughtList.value=groceriesDataBase.productsDao().getAllProducts()
        }
    }

}
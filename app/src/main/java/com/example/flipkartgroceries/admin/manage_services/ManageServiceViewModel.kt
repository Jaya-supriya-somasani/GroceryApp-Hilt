package com.example.flipkartgroceries.admin.manage_services

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class ManageServiceViewModel : ViewModel() {
    private val addCategoryEventChannel = Channel<Unit>()
    val addCategoryEvent = addCategoryEventChannel.receiveAsFlow()

    private val viewCategoriesEventChannel = Channel<Unit>()
    val viewCategoriesEvent = viewCategoriesEventChannel.receiveAsFlow()

    private val addProductEventChannel = Channel<Unit>()
    val addProductsEvent = addProductEventChannel.receiveAsFlow()

    private val viewProductsEventChannel = Channel<Unit>()
    val viewProductsChannel = viewProductsEventChannel.receiveAsFlow()

    fun addCategoryBtn(){
        addCategoryEventChannel.trySend(Unit)
    }
    fun viewCategoriesBtn(){
        viewCategoriesEventChannel.trySend(Unit)
    }
    fun addProductsBtn(){
        addProductEventChannel.trySend(Unit)
    }
    fun viewProductsBtn(){
        viewProductsEventChannel.trySend(Unit)
    }

}
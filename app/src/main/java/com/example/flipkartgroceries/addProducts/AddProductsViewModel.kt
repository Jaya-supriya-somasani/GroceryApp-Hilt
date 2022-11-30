package com.example.flipkartgroceries.addProducts

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class AddProductsViewModel : ViewModel() {
    private val addProductEventChannel=Channel<Unit>()
    val addProductEvent=addProductEventChannel.receiveAsFlow()

    fun addProductBtnClicked(){
        addProductEventChannel.trySend(Unit)
    }
}
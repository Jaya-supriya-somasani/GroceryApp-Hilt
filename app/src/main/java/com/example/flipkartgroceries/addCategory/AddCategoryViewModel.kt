package com.example.flipkartgroceries.addCategory

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class AddCategoryViewModel : ViewModel() {
    private val selectImageEventChannel = Channel<Unit>()
    val selectImageEvent = selectImageEventChannel.receiveAsFlow()

    private val submitBtnEventChannel = Channel<Unit>()
    val submitBtnEvent = submitBtnEventChannel.receiveAsFlow()

    fun selectImage(){
        selectImageEventChannel.trySend(Unit)
    }

    fun submitCategory(){
        submitBtnEventChannel.trySend(Unit)
    }
}
package com.example.flipkartgroceries.addProducts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flipkartgroceries.database.AppDataBase
import com.example.flipkartgroceries.database.ProductsEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddProductsViewModel @Inject constructor(private val groceriesDataBase: AppDataBase) : ViewModel() {
    private val addProductEventChannel = Channel<Unit>()
    val addProductEvent = addProductEventChannel.receiveAsFlow()
    val categoryType = MutableStateFlow("")
    val productName = MutableStateFlow("")
    val productWeight = MutableStateFlow("")
    val productMRP = MutableStateFlow("")
    val productPrice = MutableStateFlow("")
    val productDescription = MutableStateFlow("")
    val selectProductImage = MutableStateFlow("")
    val productId=MutableStateFlow(0)

    private val selectImageChannelEvent = Channel<Unit>()
    val selectImageEvent = selectImageChannelEvent.receiveAsFlow()

    private val toastChannelEvent=Channel<String>()
    val toastEvent=toastChannelEvent.receiveAsFlow()

    fun selectProductImage() {
        selectImageChannelEvent.trySend(Unit)
    }


    fun addProductBtnClicked() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                groceriesDataBase.productsDao().insertProducts(
                    ProductsEntity(
                        categoryName = categoryType.value,
                        productName = productName.value,
                        productWeight = productWeight.value,
                        productMRP = productMRP.value,
                        productPrice = productPrice.value,
                        description = productDescription.value,
                        productImage = selectProductImage.value,
                        productId=productId.value
                    )
                )
                addProductEventChannel.trySend(Unit)
                toastChannelEvent.trySend("Product details added Successfully")
            }
            catch (exception:Exception){
                toastChannelEvent.trySend("Unable to add product details")
            }
        }
    }
}
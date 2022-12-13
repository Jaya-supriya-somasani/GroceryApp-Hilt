package com.example.flipkartgroceries.ui.admin.manage_services.product.add_product

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flipkartgroceries.database.AppDatabase
import com.example.flipkartgroceries.database.ProductEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(private val groceriesDataBase: AppDatabase) : ViewModel() {
    private val addProductEventChannel = Channel<Unit>()
    val addProductEvent = addProductEventChannel.receiveAsFlow()
    val categoryType = MutableStateFlow("")
    val productName = MutableStateFlow("")
    val productWeight = MutableStateFlow("")
    val productMRP = MutableStateFlow("")
    val productPrice = MutableStateFlow("")
    val productDescription = MutableStateFlow("")
    val selectProductImage = MutableStateFlow("")
    val productId = MutableStateFlow(0)
    val categoryTypeError = MutableStateFlow("")
    val categoryErrorEnable = MutableStateFlow(false)
    val productNameError = MutableStateFlow("")
    val productNameErrorEnable = MutableStateFlow(false)
    val productWeightError = MutableStateFlow("")
    val productWeightErrorEnable = MutableStateFlow(false)
    val productMrpError = MutableStateFlow("")
    val productMRPErrorEnable = MutableStateFlow(false)
    val productPriceError = MutableStateFlow("")
    val productPriceErrorEnable = MutableStateFlow(false)
    val productImageError = MutableStateFlow("")


    private val selectImageChannelEvent = Channel<Unit>()
    val selectImageEvent = selectImageChannelEvent.receiveAsFlow()

    private val toastChannelEvent = Channel<String>()
    val toastEvent = toastChannelEvent.receiveAsFlow()

    fun selectProductImage() {
        selectImageChannelEvent.trySend(Unit)
    }

    fun productsValidation() {
        if (categoryType.value.isEmpty()) {
            categoryErrorEnable.value = true
            categoryTypeError.value = "Enter Category type"
            productNameError.value = ""
            productWeightError.value = ""
            productMrpError.value = ""
            productPriceError.value = ""
        } else if (productName.value.isEmpty()) {
            productNameErrorEnable.value = true
            productNameError.value = "Enter product name"
            categoryTypeError.value = ""
            productWeightError.value = ""
            productMrpError.value = ""
            productPriceError.value = ""
        } else if (productWeight.value.isEmpty()) {
            productWeightErrorEnable.value = true
            productWeightError.value = "Enter product Weight"
            productNameError.value = ""
            productMrpError.value = ""
            categoryTypeError.value = ""
            productPriceError.value = ""
        } else if (productMRP.value.isEmpty()) {
            productMRPErrorEnable.value = true
            productMrpError.value = "Enter product MRP"
            productNameError.value = ""
            productWeightError.value = ""
            categoryTypeError.value = ""
            productPriceError.value = ""
        } else if (productPrice.value.isEmpty()) {
            productPriceErrorEnable.value = true
            productPriceError.value = "Enter product Price"
            productNameError.value = ""
            productWeightError.value = ""
            productMrpError.value = ""
            categoryTypeError.value = ""
        } else if (selectProductImage.value.isEmpty()) {
            productImageError.value = "Select product image"
            productNameError.value = ""
            productWeightError.value = ""
            productMrpError.value = ""
            productPriceError.value = ""
            categoryTypeError.value = ""
            toastChannelEvent.trySend("Select Product Image")
        } else {
            productNameError.value = ""
            productWeightError.value = ""
            productMrpError.value = ""
            productPriceError.value = ""
            productImageError.value = ""
            categoryTypeError.value = ""
            addProductBtnClicked()
        }

    }


    fun addProductBtnClicked() {
        Log.d("TAG","product Id : ${productId.value}")
        if (productId.value == 0) {
            addProductDetails()
        } else {
            updateProductDetails()
        }

    }

    fun addProductDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                groceriesDataBase.productsDao().insertProducts(
                    ProductEntity(
                        categoryName = categoryType.value,
                        productName = productName.value,
                        productWeight = productWeight.value,
                        productMRP = productMRP.value,
                        productPrice = productPrice.value,
                        description = productDescription.value,
                        productImage = selectProductImage.value,
                        productId = productId.value
                    )
                )
                categoryType.value = ""
                productName.value = ""
                productWeight.value = ""
                productMRP.value = ""
                productPrice.value = ""
                productDescription.value = ""
                selectProductImage.value = ""
                addProductEventChannel.trySend(Unit)
                toastChannelEvent.trySend("Product details added Successfully")
            } catch (exception: Exception) {
                toastChannelEvent.trySend("Unable to add product details")
            }
        }

    }

    fun updateProductDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                groceriesDataBase.productsDao().updateProductDetails(
                    ProductEntity(
                        categoryName = categoryType.value,
                        productName = productName.value,
                        productWeight = productWeight.value,
                        productMRP = productMRP.value,
                        productPrice = productPrice.value,
                        description = productDescription.value,
                        productImage = selectProductImage.value,
                        productId = productId.value
                    )
                )
                addProductEventChannel.trySend(Unit)
                toastChannelEvent.trySend("Product updated Successfully")
            } catch (exception: Exception) {
                Log.d("TAG", "Exception raised while updating the product details")
            }
        }

    }
}
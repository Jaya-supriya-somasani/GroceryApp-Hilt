package com.example.flipkartgroceries.addCategory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flipkartgroceries.database.AppDataBase
import com.example.flipkartgroceries.database.CategoriesEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCategoryViewModel @Inject constructor(private val groceriesDataBase: AppDataBase) :
    ViewModel() {
    private val selectImageEventChannel = Channel<Unit>()
    val selectImageEvent = selectImageEventChannel.receiveAsFlow()

    val categoryId = MutableStateFlow(0)
    val categoryImage = MutableStateFlow("")
    val categoryName = MutableStateFlow("")
    val categoryNameError = MutableStateFlow("")
    val categoryNameErrorEnable = MutableStateFlow(false)


    private val submitBtnEventChannel = Channel<Unit>()
    val submitBtnEvent = submitBtnEventChannel.receiveAsFlow()

    private val toastEventChannel = Channel<String>()
    val toastEvent = toastEventChannel.receiveAsFlow()

    fun selectImage() {
        selectImageEventChannel.trySend(Unit)
    }

    fun addCategoryValidation() {
        if (categoryName.value.isEmpty()) {
            categoryNameError.value = "Enter Category Name"
            categoryNameErrorEnable.value=true
        }else if (categoryImage.value.isEmpty()){
            categoryNameError.value=""
            toastEventChannel.trySend("Select Category image")
        }
        else {
            categoryNameError.value = ""
            categoryNameErrorEnable.value=false
            submitCategory()
        }
    }

    fun submitCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                groceriesDataBase.categoriesDao().insertCategories(
                    CategoriesEntity(
                        categoryId = categoryId.value,
                        categoryImage = categoryImage.value,
                        categoryName = categoryName.value
                    )
                )
                categoryName.value=""
                categoryImage.value=""
                submitBtnEventChannel.trySend(Unit)

                toastEventChannel.trySend("Data is inserted Successfully")
            } catch (exception: Exception) {
                toastEventChannel.trySend("Exception raised while inserting data")
            }
        }
    }
}
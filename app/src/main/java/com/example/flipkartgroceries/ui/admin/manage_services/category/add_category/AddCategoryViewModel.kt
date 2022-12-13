package com.example.flipkartgroceries.ui.admin.manage_services.category.add_category

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flipkartgroceries.database.AppDatabase
import com.example.flipkartgroceries.database.CategoryEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCategoryViewModel @Inject constructor(private val groceriesDataBase: AppDatabase) :
    ViewModel() {
    private val selectImageEventChannel = Channel<Unit>()
    val selectImageEvent = selectImageEventChannel.receiveAsFlow()

    val categoryId = MutableStateFlow(0)
    val categoryImage = MutableStateFlow<String?>("")
    val categoryName = MutableStateFlow<String?>("")
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
        if (categoryName.value.isNullOrEmpty()) {
            categoryNameError.value = "Enter Category Name"
            categoryNameErrorEnable.value = true
        } else if (categoryImage.value.isNullOrEmpty()) {
            categoryNameError.value = ""
            toastEventChannel.trySend("Select Category image")
        } else {
            categoryNameError.value = ""
            categoryNameErrorEnable.value = false
            submitCategory()
        }
    }

    private fun submitCategory() {
        if (categoryId.value==0){
            insertData()
            Log.d("TAG","Data is inserted")
        }
        else{
            updateData()
            Log.d("TAG","Data is updated")
        }
    }

   private fun insertData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                groceriesDataBase.categoriesDao().insertCategories(
                    CategoryEntity(
                        categoryId = categoryId.value,
                        categoryImage = categoryImage.value,
                        categoryName = categoryName.value
                    )
                )
                categoryName.value = ""
                categoryImage.value = ""
                submitBtnEventChannel.trySend(Unit)
                toastEventChannel.trySend("Data is inserted Successfully")
            } catch (exception: Exception) {
                toastEventChannel.trySend("Exception raised while inserting data")
            }
        }
    }

   private fun updateData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                groceriesDataBase.categoriesDao().updateCategoryData(
                    CategoryEntity(
                        categoryId = categoryId.value,
                        categoryImage = categoryImage.value,
                        categoryName = categoryName.value
                    )
                )
                submitBtnEventChannel.trySend(Unit)
                toastEventChannel.trySend("Data is Updated")
            } catch (exception: Exception) {
                toastEventChannel.trySend("Exception is raised while updating the data")
            }
        }
    }
}
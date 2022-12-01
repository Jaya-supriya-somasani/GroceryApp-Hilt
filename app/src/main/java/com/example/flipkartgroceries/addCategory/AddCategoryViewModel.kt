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
class AddCategoryViewModel @Inject constructor(private val groceries: AppDataBase) : ViewModel() {
    private val selectImageEventChannel = Channel<Unit>()
    val selectImageEvent = selectImageEventChannel.receiveAsFlow()

    val categoryId = MutableStateFlow("")
    val categoryImage = MutableStateFlow("")
    val categoryName = MutableStateFlow("")


    private val submitBtnEventChannel = Channel<Unit>()
    val submitBtnEvent = submitBtnEventChannel.receiveAsFlow()

    fun selectImage() {
        selectImageEventChannel.trySend(Unit)
    }

    fun submitCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            groceries.categoriesDao().insertCategories(
                CategoriesEntity(
                    categoryId = categoryId.value,
                    categoryImage = categoryImage.value,
                    categoryName = categoryName.value
                )
            )
            submitBtnEventChannel.trySend(Unit)
        }
    }
}
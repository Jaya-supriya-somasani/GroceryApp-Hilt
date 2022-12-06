package com.example.flipkartgroceries.user

import androidx.lifecycle.ViewModel
import com.example.flipkartgroceries.database.AppDataBase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserHomeViewModel @Inject constructor(private val dataBase: AppDataBase) : ViewModel() {

}
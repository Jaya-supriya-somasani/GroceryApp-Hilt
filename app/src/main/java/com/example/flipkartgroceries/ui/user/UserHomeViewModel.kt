package com.example.flipkartgroceries.ui.user

import androidx.lifecycle.ViewModel
import com.example.flipkartgroceries.database.AppDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserHomeViewModel @Inject constructor(private val database: AppDatabase) : ViewModel() {

}
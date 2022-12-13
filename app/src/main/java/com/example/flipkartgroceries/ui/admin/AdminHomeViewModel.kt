package com.example.flipkartgroceries.ui.admin

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class AdminHomeViewModel @Inject constructor() : ViewModel() {
    private val manageServiceEventChannel = Channel<Unit>()
    val manageServiceEvent = manageServiceEventChannel.receiveAsFlow()

    private val userHomeEventChannel = Channel<Unit>()
    val userHomeEvent = userHomeEventChannel.receiveAsFlow()

    fun onManageServiceClicked() {
        manageServiceEventChannel.trySend(Unit)
    }

    fun onUserHomeBtnClicked(){
        userHomeEventChannel.trySend(Unit)
    }
}

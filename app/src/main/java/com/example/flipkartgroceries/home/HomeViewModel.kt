package com.example.flipkartgroceries.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class HomeViewModel : ViewModel() {
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

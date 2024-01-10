package com.skipissue.mobilebanking.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import com.skipissue.mobilebanking.data.constants.State
import com.skipissue.mobilebanking.data.settings.Settings
import com.skipissue.mobilebanking.domain.HistoryUseCase
import com.skipissue.mobilebanking.domain.entity.DataX
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel@Inject constructor(private val settings: Settings, private val historyUseCase: HistoryUseCase):
    ViewModel(){
    private val _openSuccessScreenFlow= MutableSharedFlow<List<DataX>>()
    val openSuccessScreenFlow: SharedFlow<List<DataX>> = _openSuccessScreenFlow


    private val _openErrorFlow= MutableSharedFlow<Int>()
    val openErrorFlow: SharedFlow<Int> = _openErrorFlow

    private val _openNetworkFlow= MutableSharedFlow<Unit>()
    val openNetworkFlow: SharedFlow<Unit> = _openNetworkFlow
    fun history(){
        viewModelScope.launch {
            val state = historyUseCase.invoke()
            handleState(state)
        }
    }
    private suspend fun handleState(state: State) {
        when(state){
            is State.Error -> _openErrorFlow.emit(state.code)
            State.NoNetwork -> _openNetworkFlow.emit(Unit)
            is State.Success<*> -> _openSuccessScreenFlow.emit(state.data as List<DataX>)
        }
    }
}
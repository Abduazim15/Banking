package uz.gita.lesson40.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import uz.gita.lesson40.data.constants.State
import uz.gita.lesson40.data.settings.Settings
import uz.gita.lesson40.domain.HistoryUseCase
import uz.gita.lesson40.domain.PayUseCase
import uz.gita.lesson40.domain.PayVerifyUseCase
import uz.gita.lesson40.domain.PaymentUseCase
import uz.gita.lesson40.domain.entity.DataX
import uz.gita.lesson40.domain.entity.HistoryResponse
import uz.gita.lesson40.domain.entity.PayEntity
import uz.gita.lesson40.domain.entity.Type
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
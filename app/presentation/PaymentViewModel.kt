package uz.gita.lesson40.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import uz.gita.lesson40.data.constants.State
import uz.gita.lesson40.data.settings.Settings
import uz.gita.lesson40.domain.PayUseCase
import uz.gita.lesson40.domain.PayVerifyUseCase
import uz.gita.lesson40.domain.PaymentUseCase
import uz.gita.lesson40.domain.entity.PayEntity
import uz.gita.lesson40.domain.entity.TransferVerifyEntity
import uz.gita.lesson40.domain.entity.Type
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(private val settings: Settings,private val payVerifyUseCase: PayVerifyUseCase,private val payUseCase: PayUseCase,private val paymentUseCase: PaymentUseCase):
    ViewModel(){
    private val _openSuccessScreenFlow= MutableSharedFlow<List<Type>?>()
    val openSuccessScreenFlow: SharedFlow<List<Type>?> = _openSuccessScreenFlow


    private val _openErrorFlow= MutableSharedFlow<Int>()
    val openErrorFlow: SharedFlow<Int> = _openErrorFlow

    private val _openNetworkFlow= MutableSharedFlow<Unit>()
    val openNetworkFlow: SharedFlow<Unit> = _openNetworkFlow
    fun payment(){
        viewModelScope.launch {
            val state = paymentUseCase.invoke()
            handleState(state)
        }
    }
    fun pay(payEntity: PayEntity){
        viewModelScope.launch {
            val state = payUseCase.invoke(payEntity)
            when(state) {
                is State.Error -> _openErrorFlow.emit(state.code)
                State.NoNetwork -> _openNetworkFlow.emit(Unit)
                is State.Success<*> -> _openSuccessScreenFlow.emit(null)
            }
        }
    }
    fun payVerify(){
        viewModelScope.launch {
            val state = payVerifyUseCase.invoke()
        }
    }

    private suspend fun handleState(state: State) {
        when(state){
            is State.Error -> _openErrorFlow.emit(state.code)
            State.NoNetwork -> _openNetworkFlow.emit(Unit)
            is State.Success<*> -> _openSuccessScreenFlow.emit(state.data as List<Type>)
        }
    }
}
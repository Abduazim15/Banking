package uz.gita.lesson40.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import uz.gita.lesson40.data.constants.State
import uz.gita.lesson40.data.settings.Settings
import uz.gita.lesson40.domain.AddCardUseCase
import uz.gita.lesson40.domain.TransferUseCase
import uz.gita.lesson40.domain.TransferVerifyUseCase
import uz.gita.lesson40.domain.UpdateCardUseCase
import uz.gita.lesson40.domain.entity.AddCardEntity
import uz.gita.lesson40.domain.entity.TransferEntity
import uz.gita.lesson40.domain.entity.TransferVerifyEntity
import uz.gita.lesson40.domain.entity.UpdateCardEntity
import javax.inject.Inject
@HiltViewModel
class CardViewModel @Inject constructor(private val settings: Settings,private val updateCardUseCase: UpdateCardUseCase,private val addCardUseCase: AddCardUseCase):ViewModel(){
    private val _openSuccessScreenFlow= MutableSharedFlow<String>()
    val openSuccessScreenFlow:SharedFlow<String> = _openSuccessScreenFlow

    private val _openVerifySuccessScreenFlow= MutableSharedFlow<String>()
    val openVerifySuccessScreenFlow:SharedFlow<String> = _openVerifySuccessScreenFlow

    private val _openErrorFlow= MutableSharedFlow<Int>()
    val openErrorFlow:SharedFlow<Int> = _openErrorFlow

    private val _openNetworkFlow= MutableSharedFlow<Unit>()
    val openNetworkFlow:SharedFlow<Unit> = _openNetworkFlow

    fun addCard(addCardEntity: AddCardEntity){
        viewModelScope.launch {
            val state = addCardUseCase.invoke(addCardEntity)
            handleState(state)

        }
    }
    fun updateCard(id: Int, entity: UpdateCardEntity){
        viewModelScope.launch {
            updateCardUseCase.invoke(entity, id)
        }
    }

    private suspend fun handleState(state: State) {
        when(state){
            is State.Error -> _openErrorFlow.emit(state.code)
            State.NoNetwork -> _openNetworkFlow.emit(Unit)
            is State.Success<*> -> _openSuccessScreenFlow.emit(state.data.toString())
        }
    }
}
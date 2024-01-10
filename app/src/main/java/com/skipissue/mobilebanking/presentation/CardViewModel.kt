package com.skipissue.mobilebanking.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import com.skipissue.mobilebanking.data.constants.State
import com.skipissue.mobilebanking.data.settings.Settings
import com.skipissue.mobilebanking.domain.AddCardUseCase
import com.skipissue.mobilebanking.domain.UpdateCardUseCase
import com.skipissue.mobilebanking.domain.entity.AddCardEntity
import com.skipissue.mobilebanking.domain.entity.UpdateCardEntity
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
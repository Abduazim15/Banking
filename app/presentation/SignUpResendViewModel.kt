package uz.gita.lesson40.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.gita.lesson40.data.constants.State
import uz.gita.lesson40.domain.SignUpResentUseCase
import javax.inject.Inject

class SignUpResendViewModel @Inject constructor(
    val signUpResentUseCase: SignUpResentUseCase
) : ViewModel() {

    private val _openVerifyLiveData = MutableLiveData<Unit>()
    val openVerifyLiveData: LiveData<Unit> get() = _openVerifyLiveData
    private val _errorLiveData = MutableLiveData<Int>()
    val errorLiveData: LiveData<Int> get() = _errorLiveData
    private val _noNetworkLiveData = MutableLiveData<Unit>()
    val noNetworkLiveData: LiveData<Unit> get() = _noNetworkLiveData


    fun signIn(password: String) {
        viewModelScope.launch {
            val state = signUpResentUseCase(password)
            handleState(state)
        }
    }

    private fun handleState(state: State) {
        when (state) {
            is State.Success<*> -> _openVerifyLiveData.postValue(Unit)
            is State.Error -> _errorLiveData.postValue(state.code)
            State.NoNetwork -> _noNetworkLiveData.postValue(Unit)
        }

    }
}
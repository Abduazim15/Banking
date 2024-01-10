package uz.gita.lesson40.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.gita.lesson40.data.constants.State
import uz.gita.lesson40.domain.SignUpUseCase
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {
    private val _openVerifyLiveData = MutableLiveData<Unit>()
    val openVerifyLiveData: LiveData<Unit> get() = _openVerifyLiveData
    private val _errorLiveData = MutableLiveData<Int>()
    val errorLiveData: LiveData<Int> get() = _errorLiveData
    private val _noNetworkLiveData = MutableLiveData<Unit>()
    val noNetworkLiveData: LiveData<Unit> get() = _noNetworkLiveData

    fun signUp(firstName: String?, lastName: String?, password: String?, phone: String?) {
        viewModelScope.launch {
            val state = signUpUseCase(firstName, lastName, password, phone)
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
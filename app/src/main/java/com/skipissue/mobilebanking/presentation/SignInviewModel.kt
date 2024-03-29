package com.skipissue.mobilebanking.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.skipissue.mobilebanking.data.constants.State
import com.skipissue.mobilebanking.domain.SignInUseCase
import javax.inject.Inject

@HiltViewModel
class SignInviewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    private val _openVerifyLiveData = MutableLiveData<Unit>()
    val openVerifyLiveData: LiveData<Unit> get() = _openVerifyLiveData
    private val _errorLiveData = MutableLiveData<Int>()
    val errorLiveData: LiveData<Int> get() = _errorLiveData
    private val _noNetworkLiveData = MutableLiveData<Unit>()
    val noNetworkLiveData: LiveData<Unit> get() = _noNetworkLiveData


    fun signIn(password: String?, phone: String?) {
        viewModelScope.launch {
            val state = signInUseCase(password, phone)
            handleState(state)
        }
    }

    private suspend fun handleState(state: State) {
        when (state) {
            is State.Success<*> -> _openVerifyLiveData.postValue(Unit)
            is State.Error -> _errorLiveData.postValue(state.code)
            State.NoNetwork -> _noNetworkLiveData.postValue(Unit)
        }

    }
}
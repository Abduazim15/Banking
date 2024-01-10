package com.skipissue.mobilebanking.domain

import android.util.Log
import com.skipissue.mobilebanking.data.constants.State
import com.skipissue.mobilebanking.data.repository.AuthRepository
import com.skipissue.mobilebanking.domain.entity.SignUpResentEntity
import java.io.IOException
import javax.inject.Inject

class SignUpResentUseCase @Inject constructor(val authRepository: AuthRepository) {
    suspend operator fun invoke(token: String): State {


        try {
            val response = authRepository.signUpResent(SignUpResentEntity(token))


            val body = response.body() as SignUpResentEntity
            authRepository.signInToken = body.token
            Log.d("Alii", " ${body.token} ")


        } catch (exception: Exception) {
            exception.printStackTrace()
            if (exception is IOException) return State.NoNetwork
            return State.Error(1)
        }
        return State.Success<Unit>()
    }

}
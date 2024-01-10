package com.skipissue.mobilebanking.domain

import com.skipissue.mobilebanking.data.constants.ErrorCodes
import com.skipissue.mobilebanking.data.constants.State
import com.skipissue.mobilebanking.data.repository.AuthRepository
import com.skipissue.mobilebanking.data.settings.Settings
import com.skipissue.mobilebanking.domain.entity.SignInEntity
import com.skipissue.mobilebanking.domain.entity.SignInResponse
import java.io.IOException
import javax.inject.Inject

class SignInUseCase @Inject constructor(val authRepository: AuthRepository, val settings: Settings) {


    suspend operator fun invoke(password: String?, phone: String?): State {

        if (password == null || password.length < 4) return State.Error(ErrorCodes.PASSWORD)
        if (phone == null || phone.length != 13) return State.Error(ErrorCodes.PHONE_NUMBER)
        try {
            val entity = SignInEntity(password, phone)
            val response = authRepository.SignIn(entity)

            if (response.code() == 422) {
                return State.Error(ErrorCodes.PASSWORD)
            }
            val body = response.body() as SignInResponse
            authRepository.signInToken = body.access_token
            settings.phone_number = phone
        } catch (exception: Exception) {
            exception.printStackTrace()
            if (exception is IOException) return State.NoNetwork
            return State.Error(1)
        }

        return State.Success<Unit>()
    }
}
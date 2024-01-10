package com.skipissue.mobilebanking.domain

import com.skipissue.mobilebanking.data.constants.ErrorCodes
import com.skipissue.mobilebanking.data.constants.State
import com.skipissue.mobilebanking.data.repository.AuthRepository
import com.skipissue.mobilebanking.domain.entity.SignUpEntity
import java.io.IOException
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(firstName: String?, lastName: String?, password: String?, phone: String?): State {
        if (firstName == null || firstName.length < 3) return State.Error(ErrorCodes.FIRST_NAME_ERROR)
        if (lastName == null || lastName.length < 3) return State.Error(ErrorCodes.LAST_NAME_ERROR)
        if (password == null || password.length < 4) return State.Error(ErrorCodes.PASSWORD)
        if (phone == null || phone.length != 13) return State.Error(ErrorCodes.PHONE_NUMBER)

        try {
            val entity = SignUpEntity(firstName, lastName, password, phone)
            val response = authRepository.signUp(entity)
            authRepository.temporaryToken = response.token
            authRepository.code = response.code
        } catch (exception: Exception) {
            exception.printStackTrace()
            if (exception is IOException) return State.NoNetwork
            return State.Error(1)
        }

        return State.Success<Unit>()
    }
}
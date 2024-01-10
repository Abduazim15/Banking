package com.skipissue.mobilebanking.domain

import com.skipissue.mobilebanking.data.constants.ErrorCodes
import com.skipissue.mobilebanking.data.constants.State
import com.skipissue.mobilebanking.data.repository.CardsRepository
import com.skipissue.mobilebanking.data.settings.Settings
import com.skipissue.mobilebanking.domain.entity.PayEntity
import java.io.IOException
import javax.inject.Inject

class PayUseCase @Inject constructor(private val repository: CardsRepository, val settings: Settings) {
    suspend operator fun invoke(payEntity: PayEntity): State {
        if (payEntity.amount < 1000) return State.Error(ErrorCodes.AMOUNT)
        if (payEntity.phone_number.length != 13) return State.Error(ErrorCodes.PHONE_NUMBER)
        if (payEntity.card_id == 0) return State.Error(ErrorCodes.INCORRECT_CARD)

        try {
            val response = repository.pay("Bearer ${settings.sigInToken}", payEntity)
            if (response.code() == 422) return State.Error(ErrorCodes.INCORRECT_CARD)
            settings.code = response.body()?.code
            settings.temporaryToken = response.body()?.token
        } catch (exception: Exception) {
            exception.printStackTrace()
            if (exception is IOException) return State.NoNetwork
            return State.Error(1)
        }

        return State.Success<Unit>()
    }
}
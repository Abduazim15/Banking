package uz.gita.lesson40.domain

import uz.gita.lesson40.data.constants.ErrorCodes
import uz.gita.lesson40.data.constants.State
import uz.gita.lesson40.data.repository.CardsRepository
import uz.gita.lesson40.data.settings.Settings
import uz.gita.lesson40.domain.entity.PayEntity
import uz.gita.lesson40.domain.entity.TransferEntity
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
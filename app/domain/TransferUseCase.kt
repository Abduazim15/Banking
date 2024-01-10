package uz.gita.lesson40.domain

import uz.gita.lesson40.data.constants.ErrorCodes
import uz.gita.lesson40.data.constants.State
import uz.gita.lesson40.data.repository.AuthRepository
import uz.gita.lesson40.data.repository.CardsRepository
import uz.gita.lesson40.data.settings.Settings
import uz.gita.lesson40.domain.entity.SignUpEntity
import uz.gita.lesson40.domain.entity.TransferEntity
import java.io.IOException
import javax.inject.Inject

class TransferUseCase@Inject constructor(private val repository: CardsRepository,val settings: Settings) {
    suspend operator fun invoke(transferEntity: TransferEntity): State {
        if (transferEntity.amount == 0 || transferEntity.amount < 1000) return State.Error(ErrorCodes.AMOUNT)
        if (transferEntity.from_card_id == null) return State.Error(ErrorCodes.CARD_NOT_GIVEN)
        if (transferEntity.pan == null || transferEntity.pan.length != 16) return State.Error(ErrorCodes.INCORRECT_CARD)

        try {
            val response = repository.transfer("Bearer ${settings.sigInToken}", transferEntity)
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
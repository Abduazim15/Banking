package uz.gita.lesson40.domain

import uz.gita.lesson40.data.constants.ErrorCodes
import uz.gita.lesson40.data.constants.State
import uz.gita.lesson40.data.repository.CardsRepository
import uz.gita.lesson40.data.settings.Settings
import uz.gita.lesson40.domain.entity.PayEntity
import uz.gita.lesson40.domain.entity.UpdateCardEntity
import java.io.IOException
import javax.inject.Inject

class UpdateCardUseCase @Inject constructor(private val repository: CardsRepository, val settings: Settings) {
    suspend operator fun invoke(entity: UpdateCardEntity, id: Int): State {
//        if (entity.name.length < 3) return State.Error(ErrorCodes.FIRST_NAME_ERROR)

        try {
            val response = repository.updateNameAndTheme("Bearer ${settings.sigInToken}",id,  entity)
        } catch (exception: Exception) {
            throw exception
            if (exception is IOException) return State.NoNetwork
            return State.Error(1)
        }

        return State.Success<Unit>()
    }
}
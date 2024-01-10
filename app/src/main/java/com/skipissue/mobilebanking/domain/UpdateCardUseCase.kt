package com.skipissue.mobilebanking.domain

import com.skipissue.mobilebanking.data.constants.State
import com.skipissue.mobilebanking.data.repository.CardsRepository
import com.skipissue.mobilebanking.data.settings.Settings
import com.skipissue.mobilebanking.domain.entity.UpdateCardEntity
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
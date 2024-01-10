package com.skipissue.mobilebanking.domain

import okio.IOException
import com.skipissue.mobilebanking.data.constants.State
import com.skipissue.mobilebanking.data.repository.CardsRepository
import com.skipissue.mobilebanking.data.settings.Settings
import javax.inject.Inject

class GetCardUseCase @Inject constructor(private val repository:CardsRepository,private val settings: Settings) {
    suspend operator fun invoke():State{

        try {
            val cards = repository.getCards("Bearer ${settings.sigInToken}")
            val data = cards.data
            return State.Success(cards.data)


        }catch (e:Exception){
            e.printStackTrace()
            if (e is IOException) return State.NoNetwork
            return State.Error(1)

        }
    }
}
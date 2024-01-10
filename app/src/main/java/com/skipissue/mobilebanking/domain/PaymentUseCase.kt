package com.skipissue.mobilebanking.domain

import com.skipissue.mobilebanking.data.constants.State
import com.skipissue.mobilebanking.data.repository.CardsRepository
import com.skipissue.mobilebanking.data.settings.Settings
import java.io.IOException
import javax.inject.Inject

class PaymentUseCase  @Inject constructor(private val repository: CardsRepository, private val settings: Settings) {
    suspend operator fun invoke(): State {

        try {
            val payment = repository.payment("Bearer ${settings.sigInToken}")
            val data = payment.body()?.data?.get(0)?.types
            return State.Success(data)


        }catch (e:Exception){
            e.printStackTrace()
            if (e is IOException) return State.NoNetwork
            return State.Error(1)

        }
    }
}
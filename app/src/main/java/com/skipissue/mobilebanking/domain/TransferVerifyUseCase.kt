package com.skipissue.mobilebanking.domain

import retrofit2.Response
import com.skipissue.mobilebanking.data.constants.State
import com.skipissue.mobilebanking.data.repository.CardsRepository
import com.skipissue.mobilebanking.data.settings.Settings
import com.skipissue.mobilebanking.domain.entity.TransferVerifyEntity
import com.skipissue.mobilebanking.domain.entity.getResponse.TransferVerifyResponse
import java.io.IOException
import javax.inject.Inject

class TransferVerifyUseCase @Inject constructor(private val repository: CardsRepository, val settings: Settings) {
    private lateinit var response : Response<TransferVerifyResponse>
    suspend operator fun invoke(): State {

        try {
            response = repository.transferVerify("Bearer ${settings.sigInToken}", TransferVerifyEntity(settings.code!!, settings.temporaryToken!!))
            if (response.code() == 422) return State.Error(1)
        } catch (exception: Exception) {
            exception.printStackTrace()
            if (exception is IOException) return State.NoNetwork
            return State.Error(1)
        }

        return State.Success(response.body()?.message)
    }
}
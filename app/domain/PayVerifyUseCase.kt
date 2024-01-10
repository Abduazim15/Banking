package uz.gita.lesson40.domain

import retrofit2.Response
import uz.gita.lesson40.data.constants.State
import uz.gita.lesson40.data.repository.CardsRepository
import uz.gita.lesson40.data.settings.Settings
import uz.gita.lesson40.domain.entity.TransferVerifyEntity
import uz.gita.lesson40.domain.entity.getResponse.TransferVerifyResponse
import java.io.IOException
import javax.inject.Inject

class PayVerifyUseCase @Inject constructor(private val repository: CardsRepository, val settings: Settings) {
    private lateinit var response : Response<TransferVerifyResponse>
    suspend operator fun invoke(): State {
        try {
            response = repository.payVerify("Bearer ${settings.sigInToken}", TransferVerifyEntity(settings.code!!, settings.temporaryToken!!))
            if (response.code() == 422) return State.Error(1)
        } catch (exception: Exception) {
            exception.printStackTrace()
            if (exception is IOException) return State.NoNetwork
            return State.Error(1)
        }

        return State.Success(response.body()?.message)
    }
}
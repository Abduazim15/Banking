package uz.gita.lesson40.domain

import uz.gita.lesson40.data.constants.State
import uz.gita.lesson40.data.repository.CardsRepository
import uz.gita.lesson40.data.settings.Settings
import java.io.IOException
import javax.inject.Inject

class HistoryUseCase @Inject constructor(private val repository: CardsRepository, val settings: Settings) {
    suspend operator fun invoke(): State {
        try {
            val response = repository.history("Bearer ${settings.sigInToken}")
            return State.Success(data = response.body()?.data)
        } catch (exception: Exception) {
            exception.printStackTrace()
            if (exception is IOException) return State.NoNetwork
            return State.Error(1)
        }
    }
}
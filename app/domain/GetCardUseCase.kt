package uz.gita.lesson40.domain

import androidx.fragment.app.Fragment
import okio.IOException
import uz.gita.lesson40.data.constants.State
import uz.gita.lesson40.data.repository.CardsRepository
import uz.gita.lesson40.data.settings.Settings
import uz.gita.lesson40.domain.entity.getResponse.GetCardsesponse
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
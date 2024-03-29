package com.skipissue.mobilebanking.domain

import okio.IOException
import com.skipissue.mobilebanking.data.constants.State
import com.skipissue.mobilebanking.data.repository.CardsRepository
import com.skipissue.mobilebanking.data.settings.Settings
import com.skipissue.mobilebanking.domain.entity.AddCardEntity
import javax.inject.Inject

class AddCardUseCase @Inject constructor(private val repository: CardsRepository,private val settings: Settings) {
    private var messege:String=""
    suspend operator fun invoke(addCardEntity: AddCardEntity):State{
        if (addCardEntity.pan.length!=16) return State.Error(5)
        if (addCardEntity.name.length<3)return State.Error(6)
        if (addCardEntity.expire_year<2023) return State.Error(7)
        if (addCardEntity.expire_month<1||addCardEntity.expire_month>12) return State.Error(8)

        try {
            val response = repository.addCards(addCardEntity, "Bearer ${settings.sigInToken}")
            val successful = response.isSuccessful
            if (!successful){
                if (response.code()==422||response.code()==400){
                    messege=response.message()
                    return State.Success(messege)
               }

            }


        }catch (e:Exception){
            e.printStackTrace()
            if(e is IOException) return State.NoNetwork

            return State.Error(5)
        }
        return State.Success(messege)

    }
}
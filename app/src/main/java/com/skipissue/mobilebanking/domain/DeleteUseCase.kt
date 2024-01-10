package com.skipissue.mobilebanking.domain

//class DeleteUseCase @Inject constructor(private val cardsRepository: CardsRepository,private val settings: Settings) {
//
//    suspend operator fun invoke(id:String):State{
//        try {
//            val response = cardsRepository.delete(id, "Bearer ${settings.sigInToken}")
//
//            if (response.isSuccessful){
//                return State.Success(response.message())
//            }
//
//        }catch (e:Exception){
//            e.printStackTrace()
//            if (e is IOException){
//                return State.NoNetwork
//            }
//        }
//        return State.Success("Xato")
//    }
//}
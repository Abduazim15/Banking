package uz.gita.lesson40.data.repository

import retrofit2.Response
import retrofit2.http.Body
import uz.gita.lesson40.domain.entity.AddCardEntity
import uz.gita.lesson40.domain.entity.CardResponse
import uz.gita.lesson40.domain.entity.HistoryResponse
import uz.gita.lesson40.domain.entity.PayEntity
import uz.gita.lesson40.domain.entity.PaymentResponse
import uz.gita.lesson40.domain.entity.TransferEntity
import uz.gita.lesson40.domain.entity.TransferResponse
import uz.gita.lesson40.domain.entity.TransferVerifyEntity
import uz.gita.lesson40.domain.entity.UpdateCardEntity
import uz.gita.lesson40.domain.entity.getResponse.GetCardsesponse
import uz.gita.lesson40.domain.entity.getResponse.HistoryByCard
import uz.gita.lesson40.domain.entity.getResponse.PayResponse
import uz.gita.lesson40.domain.entity.getResponse.TransferVerifyResponse

interface CardsRepository {
    suspend fun addCards(@Body addCardEntity: AddCardEntity,  bearerToken:String): Response<CardResponse>

    suspend fun getCards( bearerToken:String): GetCardsesponse

//    suspend fun delete(id:String,bearerToken:String):Response<String>

    suspend fun transfer(bearerToken:String, transferEntity: TransferEntity):Response<TransferResponse>
    suspend fun payment(bearerToken:String):Response<PaymentResponse>
    suspend fun pay(bearerToken:String, payEntity: PayEntity):Response<PayResponse>

    suspend fun transferVerify(bearerToken:String, transferVerifyEntity: TransferVerifyEntity):Response<TransferVerifyResponse>
    suspend fun payVerify(bearerToken:String, transferVerifyEntity: TransferVerifyEntity):Response<TransferVerifyResponse>

    suspend fun history(bearerToken: String): Response<HistoryResponse>

    suspend fun historyByCard(bearerToken: String, id: Int) : Response<HistoryByCard>
    suspend fun updateNameAndTheme(bearerToken: String, id: Int, entity: UpdateCardEntity): Response<Any>
}
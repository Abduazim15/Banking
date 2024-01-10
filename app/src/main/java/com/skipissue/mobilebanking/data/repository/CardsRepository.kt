package com.skipissue.mobilebanking.data.repository

import retrofit2.Response
import retrofit2.http.Body
import com.skipissue.mobilebanking.domain.entity.AddCardEntity
import com.skipissue.mobilebanking.domain.entity.CardResponse
import com.skipissue.mobilebanking.domain.entity.HistoryResponse
import com.skipissue.mobilebanking.domain.entity.PayEntity
import com.skipissue.mobilebanking.domain.entity.PaymentResponse
import com.skipissue.mobilebanking.domain.entity.TransferEntity
import com.skipissue.mobilebanking.domain.entity.TransferResponse
import com.skipissue.mobilebanking.domain.entity.TransferVerifyEntity
import com.skipissue.mobilebanking.domain.entity.UpdateCardEntity
import com.skipissue.mobilebanking.domain.entity.getResponse.GetCardsesponse
import com.skipissue.mobilebanking.domain.entity.getResponse.HistoryByCard
import com.skipissue.mobilebanking.domain.entity.getResponse.PayResponse
import com.skipissue.mobilebanking.domain.entity.getResponse.TransferVerifyResponse

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
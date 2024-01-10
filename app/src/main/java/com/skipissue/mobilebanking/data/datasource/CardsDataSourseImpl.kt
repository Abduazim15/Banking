package com.skipissue.mobilebanking.data.datasource

import retrofit2.Response
import com.skipissue.mobilebanking.data.api.AuthApi
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
import javax.inject.Inject

class CardsDataSourseImpl @Inject constructor(private val authApi:AuthApi): CardsDataSourse {
    override suspend fun addCards(addCardEntity: AddCardEntity, bearerToken: String): Response<CardResponse> {
        return authApi.addCard(addCardEntity,bearerToken)
    }

    override suspend fun getCards(bearerToken: String): GetCardsesponse {
        return authApi.getCards(bearerToken)
    }

    override suspend fun transfer(
        bearerToken: String,
        transferEntity: TransferEntity
    ): Response<TransferResponse> {
        return authApi.transfer(bearerToken, transferEntity)
    }

    override suspend fun transferVerify(
        bearerToken: String,
        transferVerifyEntity: TransferVerifyEntity
    ): Response<TransferVerifyResponse> {
        return authApi.transferVerify(bearerToken, transferVerifyEntity)
    }

    override suspend fun payVerify(
        bearerToken: String,
        transferVerifyEntity: TransferVerifyEntity
    ): Response<TransferVerifyResponse> {
        return authApi.payVerify(bearerToken, transferVerifyEntity)
    }

    override suspend fun payment(bearerToken: String): Response<PaymentResponse> {
        return authApi.payment(bearerToken)
    }

    override suspend fun pay(bearerToken: String, payEntity: PayEntity): Response<PayResponse> {
        return authApi.pay(bearerToken, payEntity)
    }

    override suspend fun history(bearerToken: String): Response<HistoryResponse> {
        return authApi.history(bearerToken)
    }

    override suspend fun historyByCard(bearerToken: String, id: Int): Response<HistoryByCard> {
        return authApi.historyByCard(bearerToken, id)
    }

    override suspend fun updateNameAndTheme(
        bearerToken: String,
        id: Int,
        entity: UpdateCardEntity
    ): Response<Any> {
        return authApi.updateCard(id.toString(), entity, bearerToken)
    }


//    override suspend fun delete(id: String, bearerToken: String): Response<String> {
//        return authApi.deleteCards(id,bearerToken)
//    }


}
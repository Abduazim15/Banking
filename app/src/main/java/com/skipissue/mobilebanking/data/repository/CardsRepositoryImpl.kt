package com.skipissue.mobilebanking.data.repository

import retrofit2.Response
import com.skipissue.mobilebanking.data.datasource.CardsDataSourse
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

class CardsRepositoryImpl @Inject constructor(private val dataSourse: CardsDataSourse):CardsRepository{
    override suspend fun addCards(addCardEntity: AddCardEntity, bearerToken: String): Response<CardResponse> {
        return dataSourse.addCards(addCardEntity,bearerToken)
    }

    override suspend fun getCards(bearerToken: String): GetCardsesponse {
        return dataSourse.getCards(bearerToken)
    }

//    override suspend fun delete(id: String, bearerToken: String): Response<String> {
////        return dataSourse.delete(id,bearerToken)
//    }

    override suspend fun transfer(bearerToken: String, transferEntity: TransferEntity): Response<TransferResponse> {
        return dataSourse.transfer(bearerToken, transferEntity)
    }

    override suspend fun payment(bearerToken: String): Response<PaymentResponse> {
        return dataSourse.payment(bearerToken)
    }

    override suspend fun pay(bearerToken: String, payEntity: PayEntity): Response<PayResponse> {
        return dataSourse.pay(bearerToken, payEntity)
    }

    override suspend fun transferVerify(bearerToken: String, transferVerifyEntity: TransferVerifyEntity): Response<TransferVerifyResponse> {
        return dataSourse.transferVerify(bearerToken, transferVerifyEntity)
    }

    override suspend fun payVerify(
        bearerToken: String,
        transferVerifyEntity: TransferVerifyEntity
    ): Response<TransferVerifyResponse> {
        return dataSourse.payVerify(bearerToken, transferVerifyEntity)
    }

    override suspend fun history(bearerToken: String): Response<HistoryResponse> {
        return dataSourse.history(bearerToken)
    }

    override suspend fun historyByCard(bearerToken: String, id: Int): Response<HistoryByCard> {
        return dataSourse.historyByCard(bearerToken, id)
    }

    override suspend fun updateNameAndTheme(
        bearerToken: String,
        id: Int,
        entity: UpdateCardEntity
    ) : Response<Any>{
        return dataSourse.updateNameAndTheme(bearerToken, id, entity)
    }


}
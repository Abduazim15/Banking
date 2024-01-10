package uz.gita.lesson40.data.datasource

import retrofit2.Response
import uz.gita.lesson40.data.api.AuthApi
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
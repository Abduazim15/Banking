package uz.gita.lesson40.data.api

import androidx.room.Update
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import uz.gita.lesson40.domain.entity.AddCardEntity
import uz.gita.lesson40.domain.entity.CardResponse
import uz.gita.lesson40.domain.entity.HistoryResponse
import uz.gita.lesson40.domain.entity.PayEntity
import uz.gita.lesson40.domain.entity.PaymentResponse
import uz.gita.lesson40.domain.entity.SignInEntity
import uz.gita.lesson40.domain.entity.SignInResponse
import uz.gita.lesson40.domain.entity.SignUpEntity
import uz.gita.lesson40.domain.entity.SignUpResentEntity
import uz.gita.lesson40.domain.entity.SignUpResentResponse
import uz.gita.lesson40.domain.entity.SignUpResponse
import uz.gita.lesson40.domain.entity.TransferEntity
import uz.gita.lesson40.domain.entity.TransferResponse
import uz.gita.lesson40.domain.entity.TransferVerifyEntity
import uz.gita.lesson40.domain.entity.UpdateCardEntity
import uz.gita.lesson40.domain.entity.getResponse.GetCardsesponse
import uz.gita.lesson40.domain.entity.getResponse.HistoryByCard
import uz.gita.lesson40.domain.entity.getResponse.PayResponse
import uz.gita.lesson40.domain.entity.getResponse.TransferVerifyResponse

interface AuthApi {
    @POST("auth/sign-up")
    suspend fun signUp(@Body signUpEntity: SignUpEntity): SignUpResponse

    @POST("auth/sign-in")
    suspend fun signIn(@Body signInEntity: SignInEntity): Response<SignInResponse>

    @POST("sign-up/resend")
    suspend fun signUpResent(@Body signUpResentEntity: SignUpResentEntity): Response<SignUpResentResponse>

    @POST("cards")
    suspend fun addCard(
        @Body addCardEntity: AddCardEntity,
        @Header("Authorization") bearerToken: String
    ): Response<CardResponse>

    @GET("cards")
    suspend fun getCards(@Header("Authorization") bearerToken: String): GetCardsesponse

    @POST("transfers")
    suspend fun transfer(
        @Header("Authorization") bearerToken: String,
        @Body transferEntity: TransferEntity
    ): Response<TransferResponse>

    @DELETE("cards/{id}")
    suspend fun deleteCards(
        @Path("id") id: String,
        @Header("Authorization") bearerToken: String
    ): Response<String>

    @GET("payments/categories?include=types")
    suspend fun payment(@Header("Authorization") bearerToken: String): Response<PaymentResponse>

    @GET("history")
    suspend fun history(@Header("Authorization") bearerToken: String): Response<HistoryResponse>

    @GET("history?page=:id")
    suspend fun historyByCard(
        @Header("Authorization") bearerToken: String,
        id: Int
    ): Response<HistoryByCard>

    @POST("payments/pay")
    suspend fun pay(
        @Header("Authorization") bearerToken: String,
        @Body payEntity: PayEntity
    ): Response<PayResponse>

    @POST("transfers/verify")
    suspend fun transferVerify(
        @Header("Authorization") bearerToken: String,
        @Body transferVerifyEntity: TransferVerifyEntity
    ): Response<TransferVerifyResponse>

    @POST("payments/pay/verify")
    suspend fun payVerify(
        @Header("Authorization") bearerToken: String,
        @Body transferVerifyEntity: TransferVerifyEntity
    ): Response<TransferVerifyResponse>

    @PUT("cards/{cardId}")
    suspend fun updateCard(
        @Path("cardId") cardId: String,
        @Body cardNameUpdate: UpdateCardEntity,
        @Header("Authorization") bearerToken: String
    ): Response<Any>

    @POST("profile/update-password")
    suspend fun updatePassword(@Header("Authorization") bearerToken: String): Response<HistoryResponse>

}
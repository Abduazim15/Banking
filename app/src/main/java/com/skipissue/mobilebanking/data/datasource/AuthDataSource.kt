package com.skipissue.mobilebanking.data.datasource

import retrofit2.Response
import retrofit2.http.Body
import com.skipissue.mobilebanking.domain.entity.SignInEntity
import com.skipissue.mobilebanking.domain.entity.SignInResponse
import com.skipissue.mobilebanking.domain.entity.SignUpEntity
import com.skipissue.mobilebanking.domain.entity.SignUpResentEntity
import com.skipissue.mobilebanking.domain.entity.SignUpResentResponse
import com.skipissue.mobilebanking.domain.entity.SignUpResponse

interface AuthDataSource {
    var temporaryToken: String?
    var temporaryTokenResent: String?
    var code: String?
    var codeResent: String?
    var signInToken:String?

    suspend fun signUp(signUpEntity: SignUpEntity): SignUpResponse
    suspend fun signIn(signInEntity: SignInEntity): Response<SignInResponse>
    suspend fun signUpResent(@Body signUpResentEntity: SignUpResentEntity): Response<SignUpResentResponse>


}
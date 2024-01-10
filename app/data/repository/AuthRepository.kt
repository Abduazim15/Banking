package uz.gita.lesson40.data.repository

import retrofit2.Response
import retrofit2.http.Body
import uz.gita.lesson40.domain.entity.SignInEntity
import uz.gita.lesson40.domain.entity.SignInResponse
import uz.gita.lesson40.domain.entity.SignUpEntity
import uz.gita.lesson40.domain.entity.SignUpResentEntity
import uz.gita.lesson40.domain.entity.SignUpResentResponse
import uz.gita.lesson40.domain.entity.SignUpResponse

interface AuthRepository {
    var temporaryToken: String?
    var temporaryTokenResent: String?
    var code: String?
    var codeResent: String?
    var signInToken: String?

    suspend fun signUp(signUpEntity: SignUpEntity): SignUpResponse
    suspend fun SignIn(signInEntity: SignInEntity): Response<SignInResponse>
    suspend fun signUpResent(@Body signUpResentEntity: SignUpResentEntity): Response<SignUpResentResponse>
}
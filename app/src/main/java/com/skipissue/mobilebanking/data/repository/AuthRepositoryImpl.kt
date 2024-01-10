package com.skipissue.mobilebanking.data.repository

import retrofit2.Response
import com.skipissue.mobilebanking.data.datasource.AuthDataSource
import com.skipissue.mobilebanking.domain.entity.SignInEntity
import com.skipissue.mobilebanking.domain.entity.SignInResponse
import com.skipissue.mobilebanking.domain.entity.SignUpEntity
import com.skipissue.mobilebanking.domain.entity.SignUpResentEntity
import com.skipissue.mobilebanking.domain.entity.SignUpResentResponse
import com.skipissue.mobilebanking.domain.entity.SignUpResponse
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
) : AuthRepository {
    override var temporaryToken: String?
        get() = authDataSource.temporaryToken
        set(value) {
            authDataSource.temporaryToken = value
        }

    override var temporaryTokenResent: String?
        get() = authDataSource.temporaryToken
        set(value) {
            authDataSource.temporaryToken = value
        }
    override var code: String?
        get() = authDataSource.code
        set(value) {
            authDataSource.code = value
        }
    override var codeResent: String?
        get() = authDataSource.code
        set(value) {
            authDataSource.code = value
        }

    override var signInToken: String?
        get() = authDataSource.signInToken
        set(value) {
            authDataSource.signInToken = value
        }

    override suspend fun signUp(signUpEntity: SignUpEntity): SignUpResponse {
        return authDataSource.signUp(signUpEntity)
    }

    override suspend fun SignIn(signInEntity: SignInEntity): Response<SignInResponse> {
        return authDataSource.signIn(signInEntity)
    }

    override suspend fun signUpResent(signUpResentEntity: SignUpResentEntity): Response<SignUpResentResponse> {
        return authDataSource.signUpResent(signUpResentEntity)
    }
}
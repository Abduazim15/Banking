package com.skipissue.mobilebanking.data.datasource

import retrofit2.Response
import com.skipissue.mobilebanking.data.api.AuthApi
import com.skipissue.mobilebanking.data.settings.Settings
import com.skipissue.mobilebanking.domain.entity.SignInEntity
import com.skipissue.mobilebanking.domain.entity.SignInResponse
import com.skipissue.mobilebanking.domain.entity.SignUpEntity
import com.skipissue.mobilebanking.domain.entity.SignUpResentEntity
import com.skipissue.mobilebanking.domain.entity.SignUpResentResponse
import com.skipissue.mobilebanking.domain.entity.SignUpResponse
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authApi: AuthApi, private val settings: Settings
) : AuthDataSource {
    override var temporaryToken: String?
        get() = settings.temporaryToken
        set(value) {
            settings.temporaryToken = value
        }
    override var temporaryTokenResent: String?
        get() = settings.temporaryToken
        set(value) {
            settings.temporaryToken = value
        }
    override var code: String?
        get() = settings.code
        set(value) {
            settings.code = value
        }
    override var codeResent: String?
        get() = settings.code
        set(value) {
            settings.code = value
        }

    override var signInToken: String?
        get() = settings.sigInToken
        set(value) {
            settings.sigInToken = value
        }

    override suspend fun signUp(signUpEntity: SignUpEntity): SignUpResponse {
        return authApi.signUp(signUpEntity)
    }

    override suspend fun signIn(signInEntity: SignInEntity): Response<SignInResponse> {
        return authApi.signIn(signInEntity)
    }

    override suspend fun signUpResent(signUpResentEntity: SignUpResentEntity): Response<SignUpResentResponse> {
        return authApi.signUpResent(signUpResentEntity)
    }
}
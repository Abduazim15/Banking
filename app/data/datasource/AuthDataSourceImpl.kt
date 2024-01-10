package uz.gita.lesson40.data.datasource

import retrofit2.Response
import uz.gita.lesson40.data.api.AuthApi
import uz.gita.lesson40.data.settings.Settings
import uz.gita.lesson40.domain.entity.SignInEntity
import uz.gita.lesson40.domain.entity.SignInResponse
import uz.gita.lesson40.domain.entity.SignUpEntity
import uz.gita.lesson40.domain.entity.SignUpResentEntity
import uz.gita.lesson40.domain.entity.SignUpResentResponse
import uz.gita.lesson40.domain.entity.SignUpResponse
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
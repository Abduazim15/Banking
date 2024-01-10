package uz.gita.lesson40.data.repository

import retrofit2.Response
import uz.gita.lesson40.data.datasource.AuthDataSource
import uz.gita.lesson40.domain.entity.SignInEntity
import uz.gita.lesson40.domain.entity.SignInResponse
import uz.gita.lesson40.domain.entity.SignUpEntity
import uz.gita.lesson40.domain.entity.SignUpResentEntity
import uz.gita.lesson40.domain.entity.SignUpResentResponse
import uz.gita.lesson40.domain.entity.SignUpResponse
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
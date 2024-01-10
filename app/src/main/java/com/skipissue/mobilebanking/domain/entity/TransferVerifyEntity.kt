package com.skipissue.mobilebanking.domain.entity

data class TransferVerifyEntity(
    val code: String,
    val token: String
)
package com.skipissue.mobilebanking.domain.entity

data class PayEntity(
    val amount: Int,
    val card_id: Int,
    val payment_type_id: Int,
    val phone_number: String
)
package com.skipissue.mobilebanking.domain.entity

data class TransferEntity(
    val amount: Int,
    val from_card_id: Int,
    val pan: String
)
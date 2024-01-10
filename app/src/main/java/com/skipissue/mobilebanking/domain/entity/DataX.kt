package com.skipissue.mobilebanking.domain.entity

data class DataX(
    val amount: Int,
    val card: Card,
    val id: Int,
    val is_output: Boolean
)
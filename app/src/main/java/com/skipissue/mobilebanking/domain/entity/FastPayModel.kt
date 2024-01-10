package com.skipissue.mobilebanking.domain.entity

class FastPayModel (
    val isCard: Boolean,
    val number: String,
    val serviceId: Int?
)
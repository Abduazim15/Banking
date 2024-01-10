package com.skipissue.mobilebanking.domain.entity

data class Data(
    val created_at: String,
    val icon_url: Any,
    val id: Int,
    val title: String,
    val types: List<Type>,
    val updated_at: String
)
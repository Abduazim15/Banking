package com.skipissue.mobilebanking.domain.entity

data class HistoryResponse(
    val `data`: List<DataX>,
    val links: Links,
    val meta: Meta
)
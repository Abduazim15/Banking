package com.skipissue.mobilebanking.domain.entity.getResponse

data class GetCardsesponse(
    val `data`: List<Data>,
    val links: Links,
    val meta: Meta
)
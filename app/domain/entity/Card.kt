package uz.gita.lesson40.domain.entity

data class Card(
    val amount: String? = null,
    val created_at: String? = null,
    val expire_month: Int? = null,
    val expire_year: Int? = null,
    val id: Int? = null,
    val owner: String,
    val pan: String,
    val phone_number: String,
    val updated_at: String? = null
)
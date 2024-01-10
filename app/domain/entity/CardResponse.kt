package uz.gita.lesson40.domain.entity

data class CardResponse(
    val amount: String,
    val expire_month: Int,
    val expire_year: Int,
    val id: Int,
    val name: String,
    val pan: String,
    val phone_number: String,
    val theme: Any
)
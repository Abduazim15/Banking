package uz.gita.lesson40.domain.entity

class FastPayModel (
    val isCard: Boolean,
    val number: String,
    val serviceId: Int?
)
package uz.gita.lesson40.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction")
class CardHistoryEntity (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val name: String?,
    val phoneNumber: String?,
    val cardNumber: String?,
    val isSuccess: Boolean,
    val code: Int,
    val amount: Int
    )
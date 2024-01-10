package uz.gita.lesson40.domain.entity.getResponse

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
data class Data(
    @PrimaryKey(autoGenerate = true)
    val qId: Long=0L,
    val amount: String,
    val expire_month: Int,
    val expire_year: Int,
    val id: Int,
    val name: String,
    val pan: String,
    val phone_number: String,
    val theme: Int?
)
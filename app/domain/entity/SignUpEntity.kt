package uz.gita.lesson40.domain.entity

import com.google.gson.annotations.SerializedName

data class SignUpEntity(
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("phone_number")
    val phoneNumber: String
)
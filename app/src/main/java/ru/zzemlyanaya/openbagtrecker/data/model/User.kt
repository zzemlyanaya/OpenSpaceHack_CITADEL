package ru.zzemlyanaya.openbagtrecker.data.model

import androidx.annotation.NonNull
import org.json.JSONObject


data class User(
    @NonNull
    val userId: Int,
    @NonNull
    var email: String,
    @NonNull
    var name: String,
    @NonNull
    var type: Int, //1 = person, 0 = company
    var city: String?,
    var country: String?,
    var about: String?
) {
    fun toJSON() = JSONObject(
        mapOf("userId" to userId, "email" to email, "name" to name, "type" to type,
        "city" to city, "country" to country, "about" to about)
    )
}

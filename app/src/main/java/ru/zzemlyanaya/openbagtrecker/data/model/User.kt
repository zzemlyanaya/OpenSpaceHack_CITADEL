package ru.zzemlyanaya.openbagtrecker.data.model

import androidx.annotation.NonNull
import org.json.JSONObject
import java.io.Serializable


data class User(
    @NonNull
    val userId: Int,
    @NonNull
    var email: String,
    @NonNull
    var name: String,
    @NonNull
    var type: Int, //1 = person, 0 = company, 2 = admin
) : Serializable {
    fun toJSON() = JSONObject(
        mapOf("userId" to userId, "email" to email, "name" to name, "type" to type)
    )
}

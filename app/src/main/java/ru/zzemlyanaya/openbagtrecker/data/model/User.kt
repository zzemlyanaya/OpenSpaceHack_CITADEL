package ru.zzemlyanaya.openbagtrecker.data.model

import androidx.annotation.NonNull
import androidx.annotation.Nullable
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
    @Nullable
    var fio: String,
    @Nullable
    var achievements: String, //format="a1_lvl|a1_lvl|a3_lvl|a4_lvl|..."
    @NonNull
    var coins: Int

) : Serializable {
    fun toJSON() = JSONObject(
        mapOf(
            "userId" to userId, "email" to email, "name" to name, "type" to type, "fio" to fio,
            "achievement" to achievements, "coins" to coins
        )
    )
}

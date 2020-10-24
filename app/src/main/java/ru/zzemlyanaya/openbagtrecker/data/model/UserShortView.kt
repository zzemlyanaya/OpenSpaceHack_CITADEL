package ru.zzemlyanaya.openbagtrecker.data.model

import androidx.annotation.NonNull
import java.io.Serializable

data class UserShortView(
    @NonNull
    val username: String,
    @NonNull
    val type: Int,
    @NonNull
    val coins: Int
) : Serializable
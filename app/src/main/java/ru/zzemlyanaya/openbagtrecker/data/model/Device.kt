package ru.zzemlyanaya.openbagtrecker.data.model

import androidx.annotation.NonNull
import java.io.Serializable

data class Device(
    @NonNull
    val model: String,
    @NonNull
    var os: String
) : Serializable
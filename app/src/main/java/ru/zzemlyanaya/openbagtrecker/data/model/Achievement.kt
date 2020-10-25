package ru.zzemlyanaya.openbagtrecker.data.model

import androidx.annotation.NonNull

data class Achievement(
    @NonNull
    val name: String,
    @NonNull
    var lvl: Int //from 1 to 3
)
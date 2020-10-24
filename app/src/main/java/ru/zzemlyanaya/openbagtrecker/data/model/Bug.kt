package ru.zzemlyanaya.openbagtrecker.data.model

import androidx.annotation.NonNull

data class Bug(
    @NonNull
    val id: Int,
    @NonNull
    val authorId: Int,
    @NonNull
    val typeOS: Int, //0=Android, 1=IOS
    @NonNull
    val typeBug: String,
    @NonNull
    val date: String,
    @NonNull
    val time: String,
    @NonNull
    val fio: String,
    @NonNull
    val deviceModel: String,
    @NonNull
    val deviceOS: String,
    @NonNull
    val release: String,
    @NonNull
    val name: String,
    @NonNull
    val steps: String
//    @NonNull
//    val screens
)
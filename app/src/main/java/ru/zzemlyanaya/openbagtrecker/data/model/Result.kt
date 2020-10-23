package ru.zzemlyanaya.openbagtrecker.data.model

data class Result<T>(
    val error: String?,
    val data: T?
)
package ru.zzemlyanaya.openbagtrecker.main.chats

import androidx.annotation.NonNull

data class ChatShortView(
    @NonNull
    val chatId: Int,
    @NonNull
    val userId: Int,
    @NonNull
    val toId: Int,
    @NonNull
    val toName: String,
    @NonNull
    val lastMessage: String
)

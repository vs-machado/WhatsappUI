package com.phoenix.whatsappui.domain.model

data class ChatMessage (
    val userName: String,
    val message: String,
    val photoId: Int,
    val isSender: Boolean,
    val isGroup: Boolean,
    val messageHour: String
)
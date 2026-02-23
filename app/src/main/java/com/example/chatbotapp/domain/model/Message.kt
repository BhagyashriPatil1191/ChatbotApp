package com.example.chatbotapp.domain.model

import com.example.chatbotapp.common.Constant.EMPTY

data class Message(
    val id: String = EMPTY,
    val text: String = EMPTY,
    val isUser: Boolean = false
)
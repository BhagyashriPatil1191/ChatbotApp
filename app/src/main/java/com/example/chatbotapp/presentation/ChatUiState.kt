package com.example.chatbotapp.presentation

import com.example.chatbotapp.domain.model.Message

data class ChatUiState(
    val messages: List<Message> = emptyList()
)
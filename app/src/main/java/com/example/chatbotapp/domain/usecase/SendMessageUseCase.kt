package com.example.chatbotapp.domain.usecase

import com.example.chatbotapp.domain.model.Message
import com.example.chatbotapp.domain.repository.ChatRepository

class SendMessageUseCase(
    private val repository: ChatRepository
) {
    suspend operator fun invoke(message: Message) {
        repository.sendUserMessage(message)
    }
}
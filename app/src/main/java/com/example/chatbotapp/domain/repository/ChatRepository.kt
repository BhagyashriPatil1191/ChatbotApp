package com.example.chatbotapp.domain.repository

import com.example.chatbotapp.domain.model.Message
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    fun getResponses(): Flow<List<Message>>
    suspend fun sendUserMessage(message: Message)
}
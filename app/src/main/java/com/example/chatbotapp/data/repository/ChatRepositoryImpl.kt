package com.example.chatbotapp.data.repository

import com.example.chatbotapp.domain.model.Message
import com.example.chatbotapp.domain.repository.ChatRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * ChatRepositoryImpl maintains in-memory persistence of all the messages in a MutableStateFlow.
 * We use a new list copy to trigger the StateFlow emissions correctly
 *
 * Created by Bhagyashri Patil
 */
@Singleton
class ChatRepositoryImpl @Inject constructor() : ChatRepository {
    private val messageList = MutableStateFlow<List<Message>>(emptyList())

    override fun getResponses(): StateFlow<List<Message>> = messageList.asStateFlow()

    override suspend fun sendUserMessage(message: Message) {
        // Create a new list instance to trigger StateFlow emission
        val currentList = messageList.value.toMutableList()
        currentList.add(message)
        messageList.value = currentList
    }
}
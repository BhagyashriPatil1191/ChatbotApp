package com.example.chatbotapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.identity.util.UUID
import com.example.chatbotapp.common.Constant.DELAY
import com.example.chatbotapp.domain.model.Message
import com.example.chatbotapp.domain.repository.ChatRepository
import com.example.chatbotapp.domain.usecase.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ChatViewModel.kt
 *
 * ViewModel for handling chat messages and automated-replies
 *
 * Created by Bhagyashri Patil
 */
@HiltViewModel
class ChatViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val repository: ChatRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    init {
        observeResponses()
    }

    /**
     * Sends a user message and triggers simulated auto-reply.
     *
     * - Ignores blank messages
     * - Emits user message immediately
     * - Create reply after delay
     */
    internal fun sendMessage(text: String) {
        if (text.isBlank()) return
        viewModelScope.launch {
            sendMessageUseCase.invoke(
                message = Message(
                    id = UUID.randomUUID().toString(),
                    text = text,
                    isUser = true
                )
            )
            generateAutoReply(text)
        }
    }

    internal suspend fun generateAutoReply(text: String) {
        delay(DELAY)

        sendMessageUseCase.invoke(
            message = Message(
                id = UUID.randomUUID().toString(),
                text = "Reply to : $text",
                isUser = false
            )
        )
    }

    private fun observeResponses() {
        viewModelScope.launch {
            repository.getResponses().collect {
                _uiState.value = ChatUiState(it)
            }
        }
    }
}
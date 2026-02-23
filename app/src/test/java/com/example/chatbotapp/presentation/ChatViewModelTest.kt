package com.example.chatbotapp.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.chatbotapp.data.repository.ChatRepositoryImpl
import com.example.chatbotapp.domain.usecase.SendMessageUseCase
import com.example.chatbotapp.mock.dummyText
import com.example.chatbotapp.mock.dummyTextReply
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ChatViewModelTest {

    @get:Rule
    var instantTaskExecutorRole = InstantTaskExecutorRule()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `sendMessage adds user message`() = runTest {
        val repository = ChatRepositoryImpl()
        val sendMessageUseCase = SendMessageUseCase(repository)
        val chatViewModel = ChatViewModel(sendMessageUseCase, repository)

        chatViewModel.sendMessage(dummyText)

        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(2, chatViewModel.uiState.value.messages.size)
        assertEquals(dummyText, chatViewModel.uiState.value.messages[0].text)
        assertEquals(dummyTextReply, chatViewModel.uiState.value.messages[1].text)
    }
}
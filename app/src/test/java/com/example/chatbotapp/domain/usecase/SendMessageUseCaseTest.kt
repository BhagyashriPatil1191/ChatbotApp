package com.example.chatbotapp.domain.usecase

import com.example.chatbotapp.domain.repository.ChatRepository
import com.example.chatbotapp.mock.userMessageMock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@OptIn(ExperimentalCoroutinesApi::class)
class SendMessageUseCaseTest {
    private lateinit var repo: ChatRepository
    private lateinit var useCase: SendMessageUseCase

    @Before
    fun setup() {
        repo = mock()
        useCase = SendMessageUseCase(repo)
    }

    @Test
    fun `sendUserMessage should call repository with correct message`() = runTest {
        useCase(userMessageMock)
        verify(repo).sendUserMessage(userMessageMock)
    }
}
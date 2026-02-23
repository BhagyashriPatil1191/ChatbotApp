package com.example.chatbotapp.data.repository

import com.example.chatbotapp.mock.autoReplyMessageMock
import com.example.chatbotapp.mock.userMessageMock
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ChatRepositoryImplTest {
    private lateinit var repository: ChatRepositoryImpl

    @Before
    fun setup() {
        repository = ChatRepositoryImpl()
    }

    @Test
    fun `initial getResponses should be empty list`() = runTest {
        val messages = repository.getResponses().first()
        assertTrue(messages.isEmpty())
    }

    @Test
    fun `sendUserMessage should add message to flow`() = runTest {
        repository.sendUserMessage(userMessageMock)

        val messages = repository.getResponses().first()

        assertEquals(1, messages.size)
        assertEquals(userMessageMock, messages.first())
    }

    @Test
    fun `sendUserMessage should append multiple messages`() = runTest {

        repository.sendUserMessage(userMessageMock)
        repository.sendUserMessage(autoReplyMessageMock)

        val messages = repository.getResponses().first()

        assertEquals(2, messages.size)
        assertEquals(userMessageMock, messages[0])
        assertEquals(autoReplyMessageMock, messages[1])
    }
}
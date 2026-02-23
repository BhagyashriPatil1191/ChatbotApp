package com.example.chatbotapp.domain.model

import com.example.chatbotapp.mock.autoReplyMessageMock
import com.example.chatbotapp.mock.userMessageMock
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class MessageModelTest {

    @Test
    fun `message model domain test`() = runTest {
        assertEquals("1", userMessageMock.id)
        assertEquals("Hello", userMessageMock.text)
        assertEquals(true, userMessageMock.isUser)

        assertEquals("2", autoReplyMessageMock.id)
        assertEquals("Hi again", autoReplyMessageMock.text)
        assertEquals(false, autoReplyMessageMock.isUser)
    }
}
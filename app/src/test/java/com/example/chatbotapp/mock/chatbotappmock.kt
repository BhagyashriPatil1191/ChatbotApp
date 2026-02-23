package com.example.chatbotapp.mock

import com.example.chatbotapp.domain.model.Message

internal val userMessageMock =
    Message(
        id = "1",
        text = "Hello",
        isUser = true
    )

val autoReplyMessageMock = Message(
    id = "2",
    text = "Hi again",
    isUser = false
)

internal const val dummyText = "Hello"
internal const val dummyTextReply = "Auto reply to : Hello"
package com.example.chatbotapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.chatbotapp.presentation.ChatScreen
import com.example.chatbotapp.ui.theme.ChatBotAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChatBotAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    ChatScreen()
                }
            }
        }
    }
}
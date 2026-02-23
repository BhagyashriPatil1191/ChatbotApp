package com.example.chatbotapp.di

import com.example.chatbotapp.data.repository.ChatRepositoryImpl
import com.example.chatbotapp.domain.repository.ChatRepository
import com.example.chatbotapp.domain.usecase.SendMessageUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ChatAppModule {

    @Binds
    @Singleton
    abstract fun bindChatRepository(
        impl: ChatRepositoryImpl
    ): ChatRepository

    companion object {
        @Provides
        @Singleton
        fun provideSendMessageUseCase(
            repository: ChatRepository
        ): SendMessageUseCase {
            return SendMessageUseCase(repository)
        }
    }
}
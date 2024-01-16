package com.vandemarket.chatservice.chat.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ChatMessageTest {
    @Test
    void testEqualsWithSameObject() {
        ChatMessage.ChatId chatId = new ChatMessage.ChatId(1L);
        ChatMessage chatMessage = ChatMessage.builder().chatId(chatId).build();

        Assertions.assertTrue(chatMessage.equals(chatMessage));
    }

    @Test
    void testEqualsWithSameChatId() {
        ChatMessage.ChatId chatId = new ChatMessage.ChatId(1L);
        ChatMessage chatMessage1 = ChatMessage.builder().chatId(chatId).build();
        ChatMessage chatMessage2 = ChatMessage.builder().chatId(chatId).build();

        Assertions.assertTrue(chatMessage1.equals(chatMessage2));
    }

    @Test
    void testEqualsWithNull() {
        ChatMessage.ChatId chatId = new ChatMessage.ChatId(1L);
        ChatMessage chatMessage = ChatMessage.builder().chatId(chatId).build();

        Assertions.assertFalse(chatMessage.equals(null));
    }

    @Test
    void testEqualsWithDifferentClass() {
        ChatMessage.ChatId chatId = new ChatMessage.ChatId(1L);
        ChatMessage chatMessage = ChatMessage.builder().chatId(chatId).build();
        Object otherObject = new Object();

        Assertions.assertFalse(chatMessage.equals(otherObject));
    }

    @Test
    void testEqualsWithDifferentChatId() {
        ChatMessage.ChatId chatId1 = new ChatMessage.ChatId(1L);
        ChatMessage.ChatId chatId2 = new ChatMessage.ChatId(2L);
        ChatMessage chatMessage1 = ChatMessage.builder().chatId(chatId1).build();
        ChatMessage chatMessage2 = ChatMessage.builder().chatId(chatId2).build();

        Assertions.assertFalse(chatMessage1.equals(chatMessage2));
    }

    @Test
    void testHashCodeConsistency() {
        ChatMessage.ChatId chatId = new ChatMessage.ChatId(1L);
        ChatMessage chatMessage = ChatMessage.builder().chatId(chatId).build();

        Assertions.assertEquals(chatId.hashCode(), chatMessage.hashCode());
    }
}

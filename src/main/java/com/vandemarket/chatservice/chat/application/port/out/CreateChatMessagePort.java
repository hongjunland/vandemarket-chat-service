package com.vandemarket.chatservice.chat.application.port.out;


import com.vandemarket.chatservice.chat.domain.ChatMessage;

public interface CreateChatMessagePort {
    Long createChatMessage(ChatMessage chatMessage);
}

package com.vandemarket.chatservice.chat.application.port.in;

import com.vandemarket.chatservice.chat.application.port.in.command.ChatMessageCreateCommand;

public interface ChatMessageCreateUseCase {
    Long createChatMessage(ChatMessageCreateCommand command);
}

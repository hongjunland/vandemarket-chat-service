package com.vandemarket.chatservice.chat.application.service;

import com.vandemarket.chatservice.chat.application.port.in.ChatMessageCreateUseCase;
import com.vandemarket.chatservice.chat.application.port.in.command.ChatMessageCreateCommand;
import com.vandemarket.chatservice.chat.application.port.out.CreateChatMessagePort;
import com.vandemarket.chatservice.chat.domain.ChatMessage;
import com.vandemarket.chatservice.chat.domain.ChatRoom;
import com.vandemarket.chatservice.common.annotation.UseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
class CreateChatMessageService implements ChatMessageCreateUseCase {
    private final CreateChatMessagePort createChatMessagePort;

    @Override
    public Long createChatMessage(ChatMessageCreateCommand command) {
        ChatMessage chatMessage = ChatMessage.builder()
                .chatRoomId(new ChatRoom.RoomId(command.roomId()))
                .content(command.content())
                .writer(command.from())
                .build();
        return createChatMessagePort.createChatMessage(chatMessage);
    }
}

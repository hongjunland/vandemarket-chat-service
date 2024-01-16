package com.vandemarket.chatservice.chat.application.service;

import com.vandemarket.chatservice.chat.application.port.in.ChatRoomCreateUseCase;
import com.vandemarket.chatservice.chat.application.port.in.command.ChatRoomCreateCommand;
import com.vandemarket.chatservice.chat.application.port.out.CreateChatRoomPort;
import com.vandemarket.chatservice.chat.domain.ChatRoom;
import com.vandemarket.chatservice.common.annotation.UseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
class CreateChatRoomService implements ChatRoomCreateUseCase {
    private final CreateChatRoomPort createChatRoomPort;
    @Override
    public boolean createChatRoom(ChatRoomCreateCommand command) {
        ChatRoom chatRoom = ChatRoom.builder()
                .build();
        return createChatRoomPort.createChatRoom(chatRoom);
    }

}

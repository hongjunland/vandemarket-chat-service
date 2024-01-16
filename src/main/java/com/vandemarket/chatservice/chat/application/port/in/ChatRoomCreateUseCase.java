package com.vandemarket.chatservice.chat.application.port.in;


import com.vandemarket.chatservice.chat.application.port.in.command.ChatRoomCreateCommand;

public interface ChatRoomCreateUseCase {
    boolean createChatRoom(ChatRoomCreateCommand command);
}

package com.vandemarket.chatservice.chat.application.port.out;


import com.vandemarket.chatservice.chat.domain.ChatRoom;

public interface CreateChatRoomPort {
    boolean createChatRoom(ChatRoom chatRoom);
}

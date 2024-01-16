package com.vandemarket.chatservice.chat.application.port.in;

import com.vandemarket.chatservice.chat.adapter.in.web.dto.ChatRoomListReadResponse;
import com.vandemarket.chatservice.chat.adapter.in.web.dto.ChatRoomResponse;
import com.vandemarket.chatservice.chat.application.port.in.query.ChatRoomListQuery;
import com.vandemarket.chatservice.chat.application.port.in.query.ChatRoomQuery;

import java.util.List;

public interface ChatRoomLoadUseCase {
    ChatRoomResponse getChatRoomById(ChatRoomQuery chatRoomQuery);
    ChatRoomListReadResponse getChatRoomList(ChatRoomListQuery query);
}

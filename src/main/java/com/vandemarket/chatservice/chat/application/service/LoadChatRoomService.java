package com.vandemarket.chatservice.chat.application.service;

import com.vandemarket.chatservice.chat.adapter.in.web.dto.ChatRoomItemResponse;
import com.vandemarket.chatservice.chat.adapter.in.web.dto.ChatRoomListReadResponse;
import com.vandemarket.chatservice.chat.adapter.in.web.dto.ChatRoomResponse;
import com.vandemarket.chatservice.chat.application.port.in.ChatRoomLoadUseCase;
import com.vandemarket.chatservice.chat.application.port.in.query.ChatRoomListQuery;
import com.vandemarket.chatservice.chat.application.port.in.query.ChatRoomQuery;
import com.vandemarket.chatservice.chat.application.port.out.LoadChatRoomPort;
import com.vandemarket.chatservice.chat.domain.ChatRoom;
import com.vandemarket.chatservice.common.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
class LoadChatRoomService implements ChatRoomLoadUseCase {
    private final LoadChatRoomPort loadChatRoomPort;

    @Override
    public ChatRoomResponse getChatRoomById(ChatRoomQuery query) {
        PageRequest pageRequest = PageRequest.of(query.page(), query.size());
        ChatRoom chatRoom = loadChatRoomPort.loadById(query.id(), pageRequest);

        return ChatRoomResponse.builder()
                .roomId(chatRoom.getRoomId().value())
                .build();
    }

    @Override
    public ChatRoomListReadResponse getChatRoomList(ChatRoomListQuery query) {
        PageRequest pageRequest = PageRequest.of(query.page(), query.size(), Sort.by("chatRoomId").descending());
        List<ChatRoom> chatRoomList = loadChatRoomPort.search(pageRequest);
        ChatRoomListReadResponse response = ChatRoomListReadResponse.builder()
                .messageList(chatRoomList.stream().map(chatRoom -> ChatRoomItemResponse.builder()
                                .roomId(chatRoom.getRoomId().value())
                                .build())
                        .collect(Collectors.toList()))
                .build();
        return response;
    }
}

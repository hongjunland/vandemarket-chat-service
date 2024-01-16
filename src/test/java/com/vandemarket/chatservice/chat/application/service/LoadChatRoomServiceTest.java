package com.vandemarket.chatservice.chat.application.service;

import com.vandemarket.chatservice.chat.adapter.in.web.dto.ChatRoomItemResponse;
import com.vandemarket.chatservice.chat.adapter.in.web.dto.ChatRoomListReadResponse;
import com.vandemarket.chatservice.chat.adapter.in.web.dto.ChatRoomResponse;
import com.vandemarket.chatservice.chat.application.port.in.query.ChatRoomListQuery;
import com.vandemarket.chatservice.chat.application.port.in.query.ChatRoomQuery;
import com.vandemarket.chatservice.chat.application.port.out.LoadChatRoomPort;
import com.vandemarket.chatservice.chat.domain.ChatMessage;
import com.vandemarket.chatservice.chat.domain.ChatRoom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class LoadChatRoomServiceTest {
    @InjectMocks
    private LoadChatRoomService loadChatRoomService;

    @Mock
    private LoadChatRoomPort loadChatRoomPort;

    @Test
    public void getChatRoomById_test(){
        // Given
        final Long roomId = 1L;
        final int page = 1;
        final int size = 10;
        ChatRoomQuery query = ChatRoomQuery.builder()
                .id(roomId)
                .page(page)
                .size(size)
                .build();
        PageRequest pageRequest = PageRequest.of(query.page(), query.size());
        ChatRoom chatRoom = ChatRoom.builder()
                .roomId(new ChatRoom.RoomId(roomId))
                .build();
        ChatRoomResponse expectedResult = ChatRoomResponse.builder()
                .roomId(roomId)
                .build();
        when(loadChatRoomPort.loadById(query.id(), pageRequest)).thenReturn(chatRoom);

        // When
        ChatRoomResponse result = loadChatRoomService.getChatRoomById(query);

        // Then
        verify(loadChatRoomPort, times(1)).loadById(roomId, pageRequest);
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void getChatRoomList_test(){
        // Given
        final int page = 1;
        final int size = 10;
        ChatRoomListQuery query = ChatRoomListQuery.builder()
                .page(page)
                .size(size)
                .build();
        PageRequest pageRequest = PageRequest.of(query.page(), query.size(), Sort.by("chatRoomId").descending());
        List<ChatRoom> chatRoomList = List.of(
                ChatRoom.builder().roomId(new ChatRoom.RoomId(5L)).build(),
                ChatRoom.builder().roomId(new ChatRoom.RoomId(4L)).build(),
                ChatRoom.builder().roomId(new ChatRoom.RoomId(3L)).build(),
                ChatRoom.builder().roomId(new ChatRoom.RoomId(2L)).build(),
                ChatRoom.builder().roomId(new ChatRoom.RoomId(1L)).build()
        );
        ChatRoomListReadResponse expectedResult = ChatRoomListReadResponse.builder()
                .messageList(chatRoomList.stream().map(chatRoom -> ChatRoomItemResponse.builder()
                                .roomId(chatRoom.getRoomId().value())
                                .build())
                        .collect(Collectors.toList()))
                .build();

        when(loadChatRoomPort.search(pageRequest)).thenReturn(chatRoomList);
        // When
        ChatRoomListReadResponse result = loadChatRoomService.getChatRoomList(query);
        // Then
        verify(loadChatRoomPort, times(1)).search(pageRequest);
        Assertions.assertEquals(expectedResult.messageList().size() ,result.messageList().size());
    }

}

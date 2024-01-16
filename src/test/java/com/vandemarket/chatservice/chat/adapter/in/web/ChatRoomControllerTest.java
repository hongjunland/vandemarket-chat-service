package com.vandemarket.chatservice.chat.adapter.in.web;

import com.vandemarket.chatservice.chat.adapter.in.web.dto.ChatRoomItemResponse;
import com.vandemarket.chatservice.chat.adapter.in.web.dto.ChatRoomListReadResponse;
import com.vandemarket.chatservice.chat.adapter.in.web.dto.ChatRoomResponse;
import com.vandemarket.chatservice.chat.application.port.in.ChatRoomCreateUseCase;
import com.vandemarket.chatservice.chat.application.port.in.ChatRoomLoadUseCase;
import com.vandemarket.chatservice.chat.application.port.in.command.ChatRoomCreateCommand;
import com.vandemarket.chatservice.chat.application.port.in.query.ChatRoomListQuery;
import com.vandemarket.chatservice.chat.application.port.in.query.ChatRoomQuery;
import com.vandemarket.chatservice.common.response.SuccessApiResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ChatRoomControllerTest {
    @InjectMocks
    private ChatRoomController chatRoomController;

    @Mock
    private ChatRoomCreateUseCase chatRoomCreateUseCase;
    @Mock
    private ChatRoomLoadUseCase chatRoomLoadUseCase;

    @Test
    public void createChatRoom_test() {
        // Given
        ChatRoomCreateCommand chatRoomCreateCommand = ChatRoomCreateCommand.builder().build();
        when(chatRoomCreateUseCase.createChatRoom(chatRoomCreateCommand)).thenReturn(true);
        // When
        SuccessApiResponse<?> response = chatRoomController.createChatRoom();

        // Then
        verify(chatRoomCreateUseCase, times(1));
        Assertions.assertEquals(response.getStatus(), HttpStatus.OK.value());
    }

    @Test
    void getChatRoom_test() {
        // Given
        final Long roomId = 1L;
        final int page = 1;
        final int size = 10;
        ChatRoomQuery chatRoomQuery = ChatRoomQuery.builder()
                .id(roomId)
                .page(page)
                .size(size)
                .build();
        ChatRoomResponse expectedChatRoomResponse = ChatRoomResponse.builder().roomId(roomId).build();
        when(chatRoomLoadUseCase.getChatRoomById(chatRoomQuery)).thenReturn(expectedChatRoomResponse);
        // When
        SuccessApiResponse<ChatRoomResponse> response = (SuccessApiResponse<ChatRoomResponse>) chatRoomController.getChatRoom(roomId, page, size);

        // Then
        verify(chatRoomLoadUseCase, times(1)).getChatRoomById(chatRoomQuery);
        Assertions.assertEquals(response.getStatus(), HttpStatus.OK.value());
        Assertions.assertEquals(response.getData().roomId(), roomId);
    }

    @Test
    void getChatRoomList_test() {
        // Given
        final int page = 1;
        final int size = 10;
        ChatRoomListQuery chatRoomListQuery = ChatRoomListQuery.builder()
                .page(page)
                .size(size)
                .build();
        ChatRoomListReadResponse expectedChatRoomListResponse = ChatRoomListReadResponse.builder()
                .messageList(List.of(
                        ChatRoomItemResponse.builder().roomId(3L).build(),
                        ChatRoomItemResponse.builder().roomId(2L).build(),
                        ChatRoomItemResponse.builder().roomId(1L).build()
                ))
                .hasNext(false)
                .build();
        when(chatRoomLoadUseCase.getChatRoomList(chatRoomListQuery)).thenReturn(expectedChatRoomListResponse);
        // When
        SuccessApiResponse<ChatRoomListReadResponse> response = (SuccessApiResponse<ChatRoomListReadResponse>) chatRoomController.getChatRoomList(page, size);

        // Then
        verify(chatRoomLoadUseCase, times(1)).getChatRoomList(chatRoomListQuery);
        Assertions.assertEquals(response.getStatus(), HttpStatus.OK.value());
        Assertions.assertEquals(response.getData().messageList().size(), 3);
        Assertions.assertTrue(!response.getData().hasNext());
    }

}

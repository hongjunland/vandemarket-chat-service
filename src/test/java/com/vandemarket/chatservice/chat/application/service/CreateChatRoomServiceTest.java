package com.vandemarket.chatservice.chat.application.service;

import com.vandemarket.chatservice.chat.application.port.in.command.ChatRoomCreateCommand;
import com.vandemarket.chatservice.chat.application.port.out.CreateChatRoomPort;
import com.vandemarket.chatservice.chat.domain.ChatRoom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class CreateChatRoomServiceTest {
    @InjectMocks
    private CreateChatRoomService createChatRoomService;

    @Mock
    private CreateChatRoomPort createChatRoomPort;

    @Test
    public void createChatRoom_test(){
        // Given
        ChatRoomCreateCommand command = ChatRoomCreateCommand.builder().build();
        when(createChatRoomPort.createChatRoom(any(ChatRoom.class))).thenReturn(true);
        // When
        boolean result = createChatRoomService.createChatRoom(command);
        // Then
        verify(createChatRoomPort, times(1)).createChatRoom(any(ChatRoom.class));
        Assertions.assertTrue(result);
    }
}

package com.vandemarket.chatservice.chat.application.service;

import com.vandemarket.chatservice.chat.application.port.in.ChatMessageCreateUseCase;
import com.vandemarket.chatservice.chat.application.port.in.command.ChatMessageCreateCommand;
import com.vandemarket.chatservice.chat.application.port.out.CreateChatMessagePort;
import com.vandemarket.chatservice.chat.domain.ChatMessage;
import com.vandemarket.chatservice.chat.domain.ChatRoom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class CreateChatMessageServiceTest {
    @InjectMocks
    private CreateChatMessageService createChatMessageService;

    @Mock
    private CreateChatMessagePort createChatMessagePort;

    @Test
    public void testCreateChatMessage(){
        // Given
        final Long roomId = 1L;
        final Long chatId = 11L;
        final String content = "content1";
        ChatMessageCreateCommand command = ChatMessageCreateCommand.builder()
                .roomId(roomId)
                .content(content)
                .from("유저1")
                .build();
        when(createChatMessagePort.createChatMessage(any(ChatMessage.class))).thenReturn(chatId);

        // When
        Long result = createChatMessageService.createChatMessage(command);

        // Then
        verify(createChatMessagePort, times(1)).createChatMessage(any(ChatMessage.class));
        Assertions.assertEquals(result, chatId);
    }

}

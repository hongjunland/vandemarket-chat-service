package com.vandemarket.chatservice.chat.adapter.in.web;


import com.vandemarket.chatservice.chat.adapter.in.web.dto.ChatMessageRequest;
import com.vandemarket.chatservice.chat.adapter.in.web.dto.ChatMessageResponse;
import com.vandemarket.chatservice.chat.application.port.in.ChatMessageCreateUseCase;
import com.vandemarket.chatservice.chat.application.port.in.command.ChatMessageCreateCommand;
import com.vandemarket.chatservice.chat.application.port.out.CreateChatMessagePort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ChatControllerTest {
    @InjectMocks
    private ChatController chatController;

    @Mock
    private ChatMessageCreateUseCase chatMessageCreateUseCase;

    @Test
    public void sendMessage_test(){
        // Given
        Long roomId = 1L;
        Long chatId = 11L;
        String text = "content1";
        String from = "writer1";
        ChatMessageRequest chatMessageRequest = ChatMessageRequest.builder()
                .text(text)
                .from(from)
                .build();
        ChatMessageResponse expectedResponse = ChatMessageResponse.builder()
                .id(chatId)
                .content(chatMessageRequest.text())
                .writer(chatMessageRequest.from())
                .build();
        when(chatMessageCreateUseCase.createChatMessage(any(ChatMessageCreateCommand.class))).thenReturn(chatId);

        // When
        ChatMessageResponse result = chatController.sendMessage(roomId, chatMessageRequest);

        // Then
        verify(chatMessageCreateUseCase,times(1)).createChatMessage(any(ChatMessageCreateCommand.class));
        Assertions.assertEquals(expectedResponse, result);
    }

}

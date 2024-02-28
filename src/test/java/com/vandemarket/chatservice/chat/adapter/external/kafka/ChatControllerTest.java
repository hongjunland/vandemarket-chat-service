package com.vandemarket.chatservice.chat.adapter.external.kafka;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.vandemarket.chatservice.chat.adapter.in.web.dto.ChatMessageRequest;
import com.vandemarket.chatservice.chat.adapter.in.web.dto.ChatMessageResponse;
import com.vandemarket.chatservice.chat.application.port.in.ChatMessageCreateUseCase;
import com.vandemarket.chatservice.chat.application.port.in.command.ChatMessageCreateCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ChatControllerTest {
    @InjectMocks
    private ChatController chatController;
    @Mock
    private ChatMessageCreateUseCase chatMessageCreateUseCase;
    @Mock
    private KafkaProducer kafkaProducer;
    @Mock
    private UserAuthenticationService userAuthenticationService;

    @Test
    public void sendMessageTest() throws JsonProcessingException {
        // Given
        Long roomId = 1L;
        Long chatId = 11L;
        String text = "content1";
        String from = "writer1";
        ChatMessageRequest chatMessageRequest = ChatMessageRequest.builder()
                .text(text)
                .from(from)
                .build();
        SimpMessageHeaderAccessor simpMessageHeaderAccessor = mock(SimpMessageHeaderAccessor.class);
        CompletableFuture<String> completableFuture = CompletableFuture.completedFuture("writer1");

        when(userAuthenticationService.getUserNickname(eq("Bearer asdsadasdsadsada"))).thenReturn(completableFuture);
        when(simpMessageHeaderAccessor.getFirstNativeHeader("Authorization")).thenReturn("Bearer asdsadasdsadsada");
        when(chatMessageCreateUseCase.createChatMessage(any(ChatMessageCreateCommand.class))).thenReturn(chatId);
        doNothing().when(kafkaProducer)
                .publishMessage(eq("chat.room.message.sending"), any(ChatMessageResponse.class));
        // When
        chatController.sendMessage(roomId, chatMessageRequest, simpMessageHeaderAccessor);

        // Then
        verify(chatMessageCreateUseCase,times(1)).createChatMessage(any(ChatMessageCreateCommand.class));
    }

}

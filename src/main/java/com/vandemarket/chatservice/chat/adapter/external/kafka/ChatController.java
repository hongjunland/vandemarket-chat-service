package com.vandemarket.chatservice.chat.adapter.external.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vandemarket.chatservice.chat.adapter.in.web.dto.ChatMessageRequest;
import com.vandemarket.chatservice.chat.adapter.in.web.dto.ChatMessageResponse;
import com.vandemarket.chatservice.chat.application.port.in.ChatMessageCreateUseCase;
import com.vandemarket.chatservice.chat.application.port.in.command.ChatMessageCreateCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
class ChatController {
    private final ChatMessageCreateUseCase chatMessageCreateUseCase;
    private final KafkaProducer kafkaProducer;
    private final UserAuthenticationService userAuthenticationService;

    @MessageMapping("/chat/rooms/{roomId}/send")
    public void sendMessage(@DestinationVariable Long roomId, @Payload ChatMessageRequest chatMessage, SimpMessageHeaderAccessor headerAccessor) throws JsonProcessingException {
        String token = headerAccessor.getFirstNativeHeader("Authorization");
        userAuthenticationService.getUserNickname(token)
                .thenAccept(nickname -> {
                    ChatMessageCreateCommand chatMessageCreateCommand = ChatMessageCreateCommand.builder()
                            .content(chatMessage.text())
                            .from(nickname)
                            .roomId(roomId)
                            .build();
                    Long chatId = chatMessageCreateUseCase.createChatMessage(chatMessageCreateCommand); // DB에 등록 후 Chat Message Id 반환
                    ChatMessageResponse chatMessageResponse = ChatMessageResponse.builder()
                            .id(chatId)
                            .roomId(roomId)
                            .content(chatMessage.text())
                            .writer(nickname)
                            .build();
                    kafkaProducer.publishMessage("chat.room.message.sending", chatMessageResponse);
                });
    }
}

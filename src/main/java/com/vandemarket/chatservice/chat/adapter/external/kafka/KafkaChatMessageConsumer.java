package com.vandemarket.chatservice.chat.adapter.external.kafka;

import com.vandemarket.chatservice.chat.adapter.in.web.dto.ChatMessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@RequiredArgsConstructor
class KafkaChatMessageConsumer {
    private final SimpMessagingTemplate simpMessagingTemplate;
    @KafkaListener(topics = "chat.room.message.sending")
    public void sendMessage(ChatMessageResponse chatMessageResponse) {
        simpMessagingTemplate.convertAndSend("/topic/public/rooms/"+chatMessageResponse.roomId(), chatMessageResponse);
    }
}

package com.vandemarket.chatservice.chat.adapter.external.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vandemarket.chatservice.chat.adapter.in.web.dto.UserInfoMessage;
import com.vandemarket.chatservice.chat.adapter.in.web.dto.UserInfoRequestMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
class UserAuthenticationService {
    private final Map<String, CompletableFuture<String>> nicknameRequests;
    private final KafkaProducer kafkaProducer;
    public CompletableFuture<String> getUserNickname(String token) throws JsonProcessingException {
        // 인증 정보 요청 로직
        String requestId = UUID.randomUUID().toString();
        CompletableFuture<String> future = new CompletableFuture<>();
        nicknameRequests.put(requestId, future);
        UserInfoRequestMessage message = UserInfoRequestMessage.builder()
                .requestId(requestId)
                .token(token)
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonMessage = objectMapper.writeValueAsString(message);
        kafkaProducer.publishMessage("auth.user.info.request", jsonMessage);
        return future;
    }

    @KafkaListener(topics = "auth.user.info.response")
    public void consumeUserResponse(String message) throws JsonProcessingException {
        // 인증 정보 수신 로직
        ObjectMapper objectMapper = new ObjectMapper();
        UserInfoMessage userInfoRequestMessage = objectMapper.readValue(message, UserInfoMessage.class);
        String requestId = userInfoRequestMessage.getRequestId();
        CompletableFuture<String> future = nicknameRequests.remove(requestId);
        if (future != null) {
            future.complete(userInfoRequestMessage.getNickname());
        }
    }
}

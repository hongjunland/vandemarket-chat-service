package com.vandemarket.chatservice.chat.adapter.external.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vandemarket.chatservice.chat.adapter.in.web.dto.UserInfoMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class UserAuthenticationServiceTest {
    @Mock
    private Map<String, CompletableFuture<String>> nicknameRequests;
    @Mock
    private KafkaProducer kafkaProducer;
    @InjectMocks
    private UserAuthenticationService userAuthenticationService;
    @Captor
    private ArgumentCaptor<String> messageCaptor;
    @Test
    public void getUserNickname_test() throws JsonProcessingException, ExecutionException, InterruptedException {
        //Given
        String token = "Bearer dasdasdsads";

        //When
        CompletableFuture<String> future = userAuthenticationService.getUserNickname(token);


        //Then
        verify(kafkaProducer).publishMessage(eq("auth.user.info.request"), messageCaptor.capture());

        String capturedMessage = messageCaptor.getValue();
        assertNotNull(capturedMessage);

        String expectedNickname = "testNickname";
        future.complete(expectedNickname); // 테스트를 위한 시뮬레이션

        assertEquals(expectedNickname, future.get());
    }
    @Test
    public void consumeUserResponse_test() throws JsonProcessingException, ExecutionException, InterruptedException {
        // Given
        String requestId = UUID.randomUUID().toString();
        String expectedNickname = "JohnDoe";
        nicknameRequests.put(requestId, mock(CompletableFuture.class));
        ObjectMapper objectMapper = new ObjectMapper();
        String message = objectMapper.writeValueAsString(
                UserInfoMessage.builder()
                        .requestId(requestId)
                        .nickname(expectedNickname)
                        .build()
        );
        CompletableFuture<String> future = new CompletableFuture<>();
        when(nicknameRequests.remove(anyString())).thenReturn(future);

        // When
        userAuthenticationService.consumeUserResponse(message);

        // Then
        assertNotNull(future);
        assertTrue(future.isDone());
        assertEquals(expectedNickname, future.get());
    }
    @Test
    void consumeUserResponse_ShouldHandleNullFutureGracefully() throws Exception {
        // Given
        String requestId = UUID.randomUUID().toString();
        String nickname = "NonExistent";

        // requestId에 대응하는 CompletableFuture가 맵에 없는 경우를 시뮬레이션
        // 즉, nicknameRequests에 requestId와 매핑되는 항목이 없습니다.

        String message = new ObjectMapper().writeValueAsString(UserInfoMessage.builder()
                .requestId(requestId)
                .nickname(nickname)
                .build());

        // When & Then
        // future가 null인 경우에 대한 처리를 시뮬레이션하며, 이 때 예외가 발생하지 않아야 합니다.
        assertDoesNotThrow(
                () -> userAuthenticationService.consumeUserResponse(message));
    }
}

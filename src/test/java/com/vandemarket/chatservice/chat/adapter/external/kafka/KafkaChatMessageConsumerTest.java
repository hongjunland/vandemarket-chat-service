package com.vandemarket.chatservice.chat.adapter.external.kafka;

import com.vandemarket.chatservice.chat.adapter.in.web.dto.ChatMessageResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class KafkaChatMessageConsumerTest {
    @Mock
    private SimpMessagingTemplate simpMessagingTemplate;
    @InjectMocks
    private KafkaChatMessageConsumer kafkaChatMessageConsumer;

    @Test
    public void sendMessageTest(){
        //Given
        ChatMessageResponse mockedChatMessageResponse = mock(ChatMessageResponse.class);
        when(mockedChatMessageResponse.roomId()).thenReturn(200L);
        doNothing().when(simpMessagingTemplate)
                .convertAndSend(anyString(), eq(mockedChatMessageResponse));
        //When
        kafkaChatMessageConsumer.sendMessage(mockedChatMessageResponse);

        //Then
        verify(simpMessagingTemplate).convertAndSend(anyString(), eq(mockedChatMessageResponse));
    }

}

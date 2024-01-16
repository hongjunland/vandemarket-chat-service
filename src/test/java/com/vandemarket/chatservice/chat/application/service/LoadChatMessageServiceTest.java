package com.vandemarket.chatservice.chat.application.service;

import com.vandemarket.chatservice.chat.adapter.in.web.dto.ChatMessageResponse;
import com.vandemarket.chatservice.chat.application.port.in.query.ChatMessageListQuery;
import com.vandemarket.chatservice.chat.application.port.out.LoadChatMessagePort;
import com.vandemarket.chatservice.chat.domain.ChatMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class LoadChatMessageServiceTest {
    @InjectMocks
    private LoadChatMessageService loadChatMessageService;

    @Mock
    private LoadChatMessagePort loadChatMessagePort;

    @Test
    public void getChatMessageList_test(){
        // Given
        final Long roomId = 1L;
        final int page = 1;
        final int size = 10;
        ChatMessageListQuery query = ChatMessageListQuery.builder()
                .roomId(roomId)
                .page(page)
                .size(size)
                .build();
        PageRequest pageRequest = PageRequest.of(query.page(), query.size(), Sort.by("chatMessageId").descending());
        List<ChatMessage> expectedResultList = List.of(
                ChatMessage.builder().chatId(new ChatMessage.ChatId(1L)).build(),
                ChatMessage.builder().chatId(new ChatMessage.ChatId(2L)).build(),
                ChatMessage.builder().chatId(new ChatMessage.ChatId(3L)).build(),
                ChatMessage.builder().chatId(new ChatMessage.ChatId(4L)).build(),
                ChatMessage.builder().chatId(new ChatMessage.ChatId(5L)).build()
        );
        when(loadChatMessagePort.loadChatMessageList(roomId, pageRequest)).thenReturn(expectedResultList);
        // When
        List<ChatMessageResponse> resultList = loadChatMessageService.getChatMessageList(query);
        // Then
        verify(loadChatMessagePort, times(1)).loadChatMessageList(roomId, pageRequest);
        Assertions.assertEquals(expectedResultList.size(), resultList.size());
    }
}

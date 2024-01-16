package com.vandemarket.chatservice.chat.adapter.in.web;

import com.vandemarket.chatservice.chat.adapter.in.web.dto.ChatMessageResponse;
import com.vandemarket.chatservice.chat.application.port.in.ChatMessageLoadUseCase;
import com.vandemarket.chatservice.chat.application.port.in.query.ChatMessageListQuery;
import com.vandemarket.chatservice.common.response.SuccessApiResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ChatMessageControllerTest {
    @InjectMocks
    private ChatMessageController chatMessageController;

    @Mock
    private ChatMessageLoadUseCase chatMessageLoadUseCase;

    @Test
    public void getMessageList_test(){
        // Given
        final Long roomId = 1L;
        final int page = 1;
        final int size = 10;
        ChatMessageListQuery query = ChatMessageListQuery.builder()
                .roomId(roomId)
                .page(page)
                .size(size)
                .build();
        when(chatMessageLoadUseCase.getChatMessageList(query)).thenReturn(List.of(
                ChatMessageResponse.builder().id(1L).build(),
                ChatMessageResponse.builder().id(2L).build()
        ));

        // When
        SuccessApiResponse<List<?>> response = (SuccessApiResponse<List<?>>) chatMessageController.getMessageList(roomId, page, size);

        // Then
        verify(chatMessageLoadUseCase, times(1)).getChatMessageList(query);
        Assertions.assertEquals(response.getData().size(), 2);
    }
}

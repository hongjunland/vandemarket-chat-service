package com.vandemarket.chatservice.chat.adapter.out.persistence;

import com.vandemarket.chatservice.chat.domain.ChatMessage;
import org.assertj.core.util.diff.Chunk;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ChatMessageLoadPersistenceAdapterTest {
    @InjectMocks
    private ChatMessageLoadPersistenceAdapter chatMessageLoadPersistenceAdapter;
    @Mock
    private SpringDataChatRoomRepository springDataChatRoomRepository;
    @Mock
    private SpringDataChatMessageRepository springDataChatMessageRepository;

    @Test
    public void loadChatMessageList_test(){
        // Given
        final Long roomId = 1L;
        final PageRequest pageRequest = PageRequest.of(1,10);
        ChatRoomJpaEntity chatRoomJpaEntity = ChatRoomJpaEntity.builder()
                .chatRoomId(roomId)
                .build();
        Slice<ChatMessageJpaEntity> chatMessageJpaEntitySlice = new SliceImpl<>(Collections.singletonList(
                ChatMessageJpaEntity.builder().chatMessageId(1L).build()
        ));
        when(springDataChatRoomRepository.findById(roomId)).thenReturn(Optional.of(chatRoomJpaEntity));
        when(springDataChatMessageRepository.findAllByChatRoom(chatRoomJpaEntity, pageRequest)).thenReturn(chatMessageJpaEntitySlice);
        // When
        List<ChatMessage> resultList = chatMessageLoadPersistenceAdapter.loadChatMessageList(roomId, pageRequest);
        // Then
        verify(springDataChatRoomRepository, times(1)).findById(1L);
        verify(springDataChatMessageRepository, times(1)).findAllByChatRoom(chatRoomJpaEntity, pageRequest);
        Assertions.assertEquals(resultList.size(), 1);
    }
}

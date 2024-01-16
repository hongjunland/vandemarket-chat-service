package com.vandemarket.chatservice.chat.adapter.out.persistence;

import com.vandemarket.chatservice.chat.application.port.out.CreateChatRoomPort;
import com.vandemarket.chatservice.chat.domain.ChatRoom;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
class ChatRoomPersistenceAdapterTest {
    @InjectMocks
    private ChatRoomPersistenceAdapter chatRoomPersistenceAdapter;
    @Mock
    private SpringDataChatRoomRepository springDataChatRoomRepository;

    @Test
    public void createChatRoom() {
        ChatRoom chatRoom = ChatRoom.builder().build();
        // Given
        when(springDataChatRoomRepository.save(any(ChatRoomJpaEntity.class))).thenReturn(any());
        // When
        boolean result = chatRoomPersistenceAdapter.createChatRoom(chatRoom);
        // Then
        verify(springDataChatRoomRepository, times(1)).save(any(ChatRoomJpaEntity.class));
        Assertions.assertTrue(result);
    }
}

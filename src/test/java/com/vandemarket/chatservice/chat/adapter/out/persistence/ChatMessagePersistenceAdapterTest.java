package com.vandemarket.chatservice.chat.adapter.out.persistence;

import com.vandemarket.chatservice.chat.domain.ChatMessage;
import com.vandemarket.chatservice.chat.domain.ChatRoom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ChatMessagePersistenceAdapterTest{
    @InjectMocks
    private ChatMessagePersistenceAdapter chatMessagePersistenceAdapter;
    @Mock
    private SpringDataChatRoomRepository springDataChatRoomRepository;

    @Test
    public void createChatMessage() {
        // Given
        final Long roomId = 1L;
        final Long chatId = 1L;
        final String content = "content1";
        ChatMessage chatMessage = ChatMessage.builder()
                .chatId(new ChatMessage.ChatId(chatId))
                .content(content)
                .chatRoomId(new ChatRoom.RoomId(roomId))
                .build();
        ChatRoomJpaEntity chatRoomJpaEntity = ChatRoomJpaEntity.builder()
                .chatRoomId(roomId)
                .build();
        when(springDataChatRoomRepository.findById(chatMessage.getChatRoomId().value())).thenReturn(Optional.of(chatRoomJpaEntity));
        ChatMessageJpaEntity chatMessageJpaEntity = ChatMessageJpaEntity.builder()
                .chatRoom(chatRoomJpaEntity)
                .content(chatMessage.getContent())
                .writer(chatMessage.getWriter())
                .build();
        Long expectedResult= chatMessageJpaEntity.getChatMessageId();
        // When
        Long result = chatMessagePersistenceAdapter.createChatMessage(chatMessage);
        // Then
        verify(springDataChatRoomRepository, times(1)).findById(chatMessage.getChatRoomId().value());
        Assertions.assertEquals(expectedResult, result);
    }
}

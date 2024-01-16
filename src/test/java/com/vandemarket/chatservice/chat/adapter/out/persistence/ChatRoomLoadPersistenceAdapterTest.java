package com.vandemarket.chatservice.chat.adapter.out.persistence;

import com.vandemarket.chatservice.chat.domain.ChatRoom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ChatRoomLoadPersistenceAdapterTest{
    @InjectMocks
    private ChatRoomLoadPersistenceAdapter chatRoomLoadPersistenceAdapter;
    @Mock
    private SpringDataChatRoomRepository springDataChatRoomRepository;
    @Test
    public void loadById_test() {
        // Given
        final Long roomId = 1L;
        final PageRequest pageRequest = PageRequest.of(1, 10);
        ChatRoomJpaEntity chatRoomJpaEntity = ChatRoomJpaEntity.builder()
                .chatRoomId(roomId)
                .build();
        ChatRoom expectedResult = ChatRoom.builder()
                .roomId(new ChatRoom.RoomId(roomId))
                .build();
        when(springDataChatRoomRepository.findById(roomId)).thenReturn(Optional.of(chatRoomJpaEntity));
        // When
        ChatRoom result = chatRoomLoadPersistenceAdapter.loadById(roomId, pageRequest);
        // Then
        verify(springDataChatRoomRepository, times(1)).findById(roomId);
        Assertions.assertEquals(expectedResult, result);
    }
    @Test
    public void search() {
        // Given
        final PageRequest pageRequest = PageRequest.of(1, 10);
        Slice<ChatRoomJpaEntity> chatRoomJpaEntityList = new SliceImpl<>(List.of(
                ChatRoomJpaEntity.builder().chatRoomId(1L).build(),
                ChatRoomJpaEntity.builder().chatRoomId(2L).build(),
                ChatRoomJpaEntity.builder().chatRoomId(3L).build()
        ));
        List<ChatRoom> expectedResult = chatRoomJpaEntityList.stream()
                .map(chatRoomJpaEntity -> ChatRoom.builder()
                        .roomId(new ChatRoom.RoomId(chatRoomJpaEntity.getChatRoomId()))
                        .build())
                .collect(Collectors.toList());
        when(springDataChatRoomRepository.findAllBy(pageRequest)).thenReturn(chatRoomJpaEntityList);
        // When
        List<ChatRoom> resultList = chatRoomLoadPersistenceAdapter.search(pageRequest);
        // Then
        verify(springDataChatRoomRepository, times(1)).findAllBy(pageRequest);
        Assertions.assertEquals(expectedResult.size(), resultList.size());
    }
}

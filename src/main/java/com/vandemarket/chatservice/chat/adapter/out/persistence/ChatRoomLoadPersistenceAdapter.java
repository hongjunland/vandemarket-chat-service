package com.vandemarket.chatservice.chat.adapter.out.persistence;

import com.vandemarket.chatservice.chat.application.port.out.LoadChatRoomPort;
import com.vandemarket.chatservice.chat.domain.ChatRoom;
import com.vandemarket.chatservice.common.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
class ChatRoomLoadPersistenceAdapter implements LoadChatRoomPort {
    private final SpringDataChatRoomRepository springDataChatRoomRepository;
    @Override
    public ChatRoom loadById(Long roomId, PageRequest pageRequest) {
        ChatRoomJpaEntity chatRoomJpaEntity = springDataChatRoomRepository.findById(roomId)
                .orElseThrow(RuntimeException::new);
        return ChatRoom.builder()
                .roomId(new ChatRoom.RoomId(chatRoomJpaEntity.getChatRoomId()))
                .build();
    }

    @Override
    public List<ChatRoom> search(PageRequest pageRequest) {
        Slice<ChatRoomJpaEntity> chatRoomJpaEntityList = springDataChatRoomRepository.findAllBy(pageRequest);
        return chatRoomJpaEntityList.stream()
                .map(chatRoomJpaEntity -> ChatRoom.builder()
                        .roomId(new ChatRoom.RoomId(chatRoomJpaEntity.getChatRoomId()))
                        .build())
                .collect(Collectors.toList());
    }
}

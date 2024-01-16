package com.vandemarket.chatservice.chat.domain;

import lombok.*;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class ChatRoom {
    private RoomId roomId;
    private List<ChatMessage> messageList;
    public record RoomId(Long value) {
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ChatRoom chatRoom = (ChatRoom) obj;
        return roomId.equals(chatRoom.getRoomId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(roomId);
    }
}

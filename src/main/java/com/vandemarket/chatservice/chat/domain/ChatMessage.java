package com.vandemarket.chatservice.chat.domain;


import lombok.*;

import java.util.Objects;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class ChatMessage{
    private ChatId chatId;
    private String writer;
    private String content;
    private ChatRoom.RoomId chatRoomId;
    public record ChatId(Long value) {
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ChatMessage chatMessage = (ChatMessage) obj;
        return chatId.equals(chatMessage.getChatId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(chatId);
    }
}


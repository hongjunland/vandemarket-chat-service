package com.vandemarket.chatservice.chat.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ChatRoomTest {
    @Test
    void testEqualsWithSameObject() {
        ChatRoom.RoomId roomId = new ChatRoom.RoomId(1L);
        ChatRoom chatRoom = ChatRoom.builder().roomId(roomId).build();

        Assertions.assertTrue(chatRoom.equals(chatRoom));
    }

    @Test
    void testEqualsWithSameRoomId() {
        ChatRoom.RoomId roomId = new ChatRoom.RoomId(1L);
        ChatRoom chatRoom1 = ChatRoom.builder().roomId(roomId).build();
        ChatRoom chatRoom2 = ChatRoom.builder().roomId(roomId).build();

        Assertions.assertTrue(chatRoom1.equals(chatRoom2));
    }

    @Test
    void testEqualsWithNull() {
        ChatRoom.RoomId roomId = new ChatRoom.RoomId(1L);
        ChatRoom chatRoom = ChatRoom.builder().roomId(roomId).build();

        Assertions.assertFalse(chatRoom.equals(null));
    }

    @Test
    void testEqualsWithDifferentClass() {
        ChatRoom.RoomId roomId = new ChatRoom.RoomId(1L);
        ChatRoom chatRoom = ChatRoom.builder().roomId(roomId).build();
        Object otherObject = new Object();

        Assertions.assertFalse(chatRoom.equals(otherObject));
    }

    @Test
    void testEqualsWithDifferentRoomId() {
        ChatRoom.RoomId roomId1 = new ChatRoom.RoomId(1L);
        ChatRoom.RoomId roomId2 = new ChatRoom.RoomId(2L);
        ChatRoom chatRoom1 = ChatRoom.builder().roomId(roomId1).build();
        ChatRoom chatRoom2 = ChatRoom.builder().roomId(roomId2).build();

        Assertions.assertFalse(chatRoom1.equals(chatRoom2));
    }

    @Test
    void testHashCodeRoomId() {
        ChatRoom.RoomId roomId1 = new ChatRoom.RoomId(1L);
        ChatRoom.RoomId roomId2 = new ChatRoom.RoomId(1L);
        ChatRoom chatRoom1 = ChatRoom.builder().roomId(roomId1).build();
        ChatRoom chatRoom2 = ChatRoom.builder().roomId(roomId2).build();

        Assertions.assertTrue(chatRoom1.hashCode() == chatRoom2.hashCode());
    }

    @Test
    void testHashCodeWithDifferentRoomId() {
        ChatRoom.RoomId roomId1 = new ChatRoom.RoomId(1L);
        ChatRoom.RoomId roomId2 = new ChatRoom.RoomId(2L);
        ChatRoom chatRoom1 = ChatRoom.builder().roomId(roomId1).build();
        ChatRoom chatRoom2 = ChatRoom.builder().roomId(roomId2).build();

        Assertions.assertFalse(chatRoom1.hashCode() == chatRoom2.hashCode());
    }

}

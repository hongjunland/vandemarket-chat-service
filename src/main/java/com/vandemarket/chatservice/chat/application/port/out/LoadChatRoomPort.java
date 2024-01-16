package com.vandemarket.chatservice.chat.application.port.out;


import com.vandemarket.chatservice.chat.domain.ChatRoom;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface LoadChatRoomPort {
    ChatRoom loadById(Long roomId, PageRequest pageRequest);
    List<ChatRoom> search(PageRequest pageRequest);
}

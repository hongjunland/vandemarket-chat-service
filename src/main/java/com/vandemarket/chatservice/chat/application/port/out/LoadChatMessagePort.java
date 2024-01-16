package com.vandemarket.chatservice.chat.application.port.out;

import com.vandemarket.chatservice.chat.domain.ChatMessage;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface LoadChatMessagePort {
    List<ChatMessage> loadChatMessageList(Long roomId, PageRequest pageRequest);
}

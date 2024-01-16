package com.vandemarket.chatservice.chat.application.port.out;

import com.vandemarket.chatservice.chat.domain.ChatMessage;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface LoadChatMessagePort {
    List<ChatMessage> loadChatMessegeList(Long roomId, PageRequest pageRequest);
}

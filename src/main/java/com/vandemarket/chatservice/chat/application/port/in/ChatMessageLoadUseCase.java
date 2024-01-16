package com.vandemarket.chatservice.chat.application.port.in;

import com.vandemarket.chatservice.chat.adapter.in.web.dto.ChatMessageResponse;
import com.vandemarket.chatservice.chat.application.port.in.query.ChatMessageListQuery;

import java.util.List;

public interface ChatMessageLoadUseCase {
    List<ChatMessageResponse> getChatMessageList(ChatMessageListQuery command);
}

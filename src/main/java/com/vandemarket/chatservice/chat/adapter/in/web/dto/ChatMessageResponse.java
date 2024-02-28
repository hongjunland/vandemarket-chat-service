package com.vandemarket.chatservice.chat.adapter.in.web.dto;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record ChatMessageResponse(Long id, Long roomId, String content, String writer) implements Serializable {
}

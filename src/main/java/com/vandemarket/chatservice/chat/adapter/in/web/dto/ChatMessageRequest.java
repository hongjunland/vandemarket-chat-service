package com.vandemarket.chatservice.chat.adapter.in.web.dto;


import lombok.Builder;

@Builder
public record ChatMessageRequest(String from, String text) {
}


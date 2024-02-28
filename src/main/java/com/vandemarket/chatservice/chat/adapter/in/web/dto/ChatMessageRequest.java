package com.vandemarket.chatservice.chat.adapter.in.web.dto;


import lombok.Builder;

import java.io.Serializable;

@Builder
public record ChatMessageRequest(String from, String text) implements Serializable {
}


package com.vandemarket.chatservice.chat.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//@Builder
//public record UserInfoRequestMessage(String requestId, String token) implements Serializable {
//}
@Builder
@Getter
public class UserInfoRequestMessage implements Serializable {
    private String requestId;
    private String token;
}


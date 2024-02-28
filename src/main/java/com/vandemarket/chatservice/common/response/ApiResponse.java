package com.vandemarket.chatservice.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public abstract class ApiResponse implements Serializable {
    protected final int status;
    protected final String message;
}

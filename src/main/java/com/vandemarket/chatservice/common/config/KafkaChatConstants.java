package com.vandemarket.chatservice.common.config;

import java.util.UUID;

public class KafkaChatConstants {
    private static String name = UUID.randomUUID().toString();
    public static final String GROUP_ID = name;
}

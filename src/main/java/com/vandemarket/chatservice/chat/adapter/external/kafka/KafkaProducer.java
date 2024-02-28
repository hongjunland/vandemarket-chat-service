package com.vandemarket.chatservice.chat.adapter.external.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class KafkaProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishMessage(String topic, Object message) {
        kafkaTemplate.send(topic, message);
    }
}

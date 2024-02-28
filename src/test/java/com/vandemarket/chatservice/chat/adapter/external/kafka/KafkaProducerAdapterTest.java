package com.vandemarket.chatservice.chat.adapter.external.kafka;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class KafkaProducerAdapterTest {
    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;
    @InjectMocks
    private KafkaProducer kafkaProducer;

    @Test
    public void publishMessageTest_object(){
        // Given
        final String topic = "topic";
        Object message = new Object();
        // When
        kafkaProducer.publishMessage(topic, message);

        // Then
        verify(kafkaTemplate).send(anyString(), eq(message));
    }
    @Test
    public void publishMessageTest_integer(){
        // Given
        final String topic = "topic";
        Integer message = 2132;
        // When
        kafkaProducer.publishMessage(topic, message);

        // Then
        verify(kafkaTemplate).send(anyString(), eq(message));
    }
    @Test
    public void publishMessageTest_string(){
        // Given
        final String topic = "topic";
        String message = "sdsd";
        // When
        kafkaProducer.publishMessage(topic, message);

        // Then
        verify(kafkaTemplate).send(anyString(), eq(message));
    }
}

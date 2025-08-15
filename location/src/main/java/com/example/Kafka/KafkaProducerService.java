package com.example.Kafka;
import com.example.DTO.DriverLocationEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
private final KafkaTemplate<String, DriverLocationEvent> kafkaTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, DriverLocationEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, DriverLocationEvent event) {
        kafkaTemplate.send(topic, event);
    }
}

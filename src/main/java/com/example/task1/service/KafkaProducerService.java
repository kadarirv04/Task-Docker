package com.example.task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendExpenseEvent(String eventType, String expenseJson) {
        String topic = "expense-events";
        String message = String.format("{\"eventType\":\"%s\", \"data\":%s}", eventType, expenseJson);
        kafkaTemplate.send(topic, message);
    }
} 
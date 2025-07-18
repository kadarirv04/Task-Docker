package com.example.task1.service;

import com.example.task1.model.Expense;
import com.example.task1.repository.ExpenseRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "expense-events", groupId = "expense-group")
    public void listen(String message) {
        try {
            JsonNode root = objectMapper.readTree(message);
            String eventType = root.get("eventType").asText();
            JsonNode data = root.get("data");
            switch (eventType) {
                case "EXPENSE_CREATED":
                    Expense expense = objectMapper.treeToValue(data, Expense.class);
                    expenseRepository.save(expense);
                    break;
                case "EXPENSE_DELETED":
                    Long id = data.get("expenseId").asLong();
                    expenseRepository.deleteById(id);
                    break;
                case "EXPENSES_DELETED":
                    expenseRepository.deleteAll();
                    break;
                default:
                    // Unknown event
            }
        } catch (Exception e) {
            // Log error
            e.printStackTrace();
        }
    }
} 
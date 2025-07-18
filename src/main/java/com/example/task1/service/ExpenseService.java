package com.example.task1.service;

import com.example.task1.model.Expense;
import com.example.task1.repository.ExpenseRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExpenseService {
    private final KafkaProducerService kafkaProducerService;
    private final ObjectMapper objectMapper;
    private final ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(KafkaProducerService kafkaProducerService, ObjectMapper objectMapper, ExpenseRepository expenseRepository) {
        this.kafkaProducerService = kafkaProducerService;
        this.objectMapper = objectMapper;
        this.expenseRepository = expenseRepository;
    }

    public void addExpense(Expense expense) {
        try {
            String json = objectMapper.writeValueAsString(expense);
            kafkaProducerService.sendExpenseEvent("EXPENSE_CREATED", json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize expense", e);
        }
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public void deleteAllExpenses() {
        kafkaProducerService.sendExpenseEvent("EXPENSES_DELETED", "{}" );
    }

    public void deleteExpense(Long id) {
        kafkaProducerService.sendExpenseEvent("EXPENSE_DELETED", String.format("{\"expenseId\":%d}", id));
    }
} 
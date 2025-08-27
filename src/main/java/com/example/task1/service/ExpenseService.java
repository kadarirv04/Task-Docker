package com.example.task1.service;

import com.example.task1.model.Expense;
import com.example.task1.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public void addExpense(Expense expense) {
        expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public void deleteAllExpenses() {
        expenseRepository.deleteAll();
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
} 
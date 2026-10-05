package com.example.ExpenseTracker.Service;

import com.example.ExpenseTracker.Controller.ExpenseController;
import com.example.ExpenseTracker.Entity.Expense;
import com.example.ExpenseTracker.Repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExpenseService {

    public ExpenseController expenseService;
    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense save(Expense expense){
        Expense savedExpense = expenseRepository.save(expense);
        return savedExpense;
    }

    public Expense deleteById(Long id){
        Expense expenseToDelete = getById(id);
        expenseRepository.deleteById(id);
        return expenseToDelete;
    }

    public void deleteAll(){
        expenseRepository.deleteAll();
    }

    public Expense getById(Long id) {
        return expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("Expense not found with"));
    }

    public Expense update(Expense newExpenseDetails, Long id){
        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));

        existingExpense.setTitle(newExpenseDetails.getTitle());
        existingExpense.setAmount(newExpenseDetails.getAmount());
        existingExpense.setDate(newExpenseDetails.getDate());

        return expenseRepository.save(existingExpense);
    }
}

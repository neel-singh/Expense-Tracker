package com.example.ExpenseTracker.Controller;

import com.example.ExpenseTracker.Entity.Expense;
import com.example.ExpenseTracker.Service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.noContent;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/save")
    public ResponseEntity<Expense> save(@RequestBody Expense expense){
        Expense savedExpense = expenseService.save(expense);
        return new ResponseEntity<>(savedExpense, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Expense> getById(@PathVariable Long id){
        Expense expense = expenseService.getById(id);
        return ResponseEntity.ok(expense);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Expense> deleteById(@PathVariable Long id){
        Expense deletedExpense = expenseService.deleteById(id);
        return ResponseEntity.ok(deletedExpense);
    }

    @DeleteMapping("delete")
    public ResponseEntity<Void> deleteAll(){
        expenseService.deleteAll();
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Expense> update(@RequestBody Expense expense, @PathVariable Long id){
        Expense updatedExpense = expenseService.update(expense, id);
        return new ResponseEntity<>(updatedExpense, HttpStatus.OK);
    }

}

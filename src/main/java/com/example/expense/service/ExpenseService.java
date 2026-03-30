package com.example.expense.service;

import com.example.expense.dto.ExpenseDTO;
import com.example.expense.entity.Expense;
import com.example.expense.exception.ResourceNotFoundException;
import com.example.expense.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    // Get all
    public List<ExpenseDTO> getAllExpenses() {
        return expenseRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Get by ID
    public ExpenseDTO getExpenseById(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + id));
        return toDTO(expense);
    }

    // Create
    public ExpenseDTO createExpense(ExpenseDTO dto) {
        return toDTO(expenseRepository.save(toEntity(dto)));
    }

    // Update
    public ExpenseDTO updateExpense(Long id, ExpenseDTO dto) {
        Expense existing = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + id));

        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());
        existing.setAmount(dto.getAmount());
        existing.setCategory(dto.getCategory());
        existing.setDate(dto.getDate());

        return toDTO(expenseRepository.save(existing));
    }

    // Delete
    public void deleteExpense(Long id) {
        if (!expenseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Expense not found with id: " + id);
        }
        expenseRepository.deleteById(id);
    }

    // Filter by category
    public List<ExpenseDTO> getByCategory(String category) {
        return expenseRepository.findByCategory(category)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Filter by date range
    public List<ExpenseDTO> getByDateRange(LocalDate startDate, LocalDate endDate) {
        return expenseRepository.findByDateBetween(startDate, endDate)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ---- Entity → DTO ----
    private ExpenseDTO toDTO(Expense e) {
        return new ExpenseDTO(
                e.getId(),
                e.getTitle(),
                e.getDescription(),
                e.getAmount(),
                e.getCategory(),
                e.getDate()
        );
    }

    // ---- DTO → Entity ----
    private Expense toEntity(ExpenseDTO dto) {
        Expense e = new Expense();
        e.setTitle(dto.getTitle());
        e.setDescription(dto.getDescription());
        e.setAmount(dto.getAmount());
        e.setCategory(dto.getCategory());
        e.setDate(dto.getDate());
        return e;
    }
}

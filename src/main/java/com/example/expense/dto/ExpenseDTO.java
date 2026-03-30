package com.example.expense.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenseDTO {

    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be a positive number")
    private BigDecimal amount;

    @NotBlank(message = "Category is required")
    private String category;

    @NotNull(message = "Date is required")
    private LocalDate date;

    // ---- Constructors ----
    public ExpenseDTO() {}

    public ExpenseDTO(Long id, String title, String description,
                      BigDecimal amount, String category, LocalDate date) {
        this.id          = id;
        this.title       = title;
        this.description = description;
        this.amount      = amount;
        this.category    = category;
        this.date        = date;
    }

    // ---- Getters ----
    public Long getId()            { return id; }
    public String getTitle()       { return title; }
    public String getDescription() { return description; }
    public BigDecimal getAmount()  { return amount; }
    public String getCategory()    { return category; }
    public LocalDate getDate()     { return date; }

    // ---- Setters ----
    public void setId(Long id)               { this.id = id; }
    public void setTitle(String title)       { this.title = title; }
    public void setDescription(String desc)  { this.description = desc; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public void setCategory(String category) { this.category = category; }
    public void setDate(LocalDate date)      { this.date = date; }
}

package com.example.expense.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 500)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private LocalDate date;

    // ---- Constructors ----
    public Expense() {}

    public Expense(Long id, String title, String description,
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
    public void setId(Long id)                   { this.id = id; }
    public void setTitle(String title)           { this.title = title; }
    public void setDescription(String desc)      { this.description = desc; }
    public void setAmount(BigDecimal amount)     { this.amount = amount; }
    public void setCategory(String category)     { this.category = category; }
    public void setDate(LocalDate date)          { this.date = date; }
}

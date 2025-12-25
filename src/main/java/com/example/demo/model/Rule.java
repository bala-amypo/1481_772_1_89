package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleType; // EXACT, CONTAINS, REGEX
    private String pattern;
    private int priority;

    @ManyToOne
    private Category category; // Make sure you have Category.java as well

    // Constructors
    public Rule() {}

    public Rule(String ruleType, String pattern, int priority, Category category) {
        this.ruleType = ruleType;
        this.pattern = pattern;
        this.priority = priority;
        this.category = category;
    }

    // Getters
    public Long getId() { return id; }
    public String getRuleType() { return ruleType; }
    public String getPattern() { return pattern; }
    public int getPriority() { return priority; }
    public Category getCategory() { return category; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setRuleType(String ruleType) { this.ruleType = ruleType; }
    public void setPattern(String pattern) { this.pattern = pattern; }
    public void setPriority(int priority) { this.priority = priority; }
    public void setCategory(Category category) { this.category = category; }
}

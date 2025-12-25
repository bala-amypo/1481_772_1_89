 package com.example.demo.model;

public class Rule {

    private String pattern;
    private Category category;
    private int priority;
    private String ruleType;

    public Rule() {}

    public Rule(String pattern, Category category, int priority, String ruleType) {
        this.pattern = pattern;
        this.category = category;
        this.priority = priority;
        this.ruleType = ruleType;
    }

    public boolean matches(String text) {
        if (text == null) return false;

        switch (ruleType.toUpperCase()) {
            case "EXACT":
                return text.equals(pattern);
            case "CONTAINS":
                return text.contains(pattern);
            case "REGEX":
                return text.matches(pattern);
            default:
                return false;
        }
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }
}

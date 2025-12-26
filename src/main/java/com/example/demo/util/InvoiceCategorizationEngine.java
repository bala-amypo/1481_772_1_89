 package com.example.demo.util;

import com.example.demo.model.CategorizationRule;
import com.example.demo.model.Category;
import com.example.demo.model.Invoice;
import com.example.demo.model.MatchType;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class InvoiceCategorizationEngine {

    public Category determineCategory(Invoice invoice, List<CategorizationRule> rules) {

        return rules.stream()
                .sorted(Comparator.comparingInt(CategorizationRule::getPriority))
                .filter(rule -> matches(rule, invoice.getDescription()))
                .map(CategorizationRule::getCategory)
                .findFirst()
                .orElse(null);
    }

    private boolean matches(CategorizationRule rule, String description) {

        if (description == null) return false;

        return switch (rule.getMatchType()) {
            case EXACT -> description.equalsIgnoreCase(rule.getKeyword());
            case CONTAINS -> description.toLowerCase().contains(rule.getKeyword().toLowerCase());
            case REGEX -> description.matches(rule.getKeyword());
        };
    }
}

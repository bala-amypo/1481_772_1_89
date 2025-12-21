 package com.example.demo.util;

import com.example.demo.model.*;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class InvoiceCategorizationEngine {

    public Category determineCategory(Invoice invoice, List<CategorizationRule> rules) {

        return rules.stream()
                .sorted(Comparator.comparingInt(CategorizationRule::getPriority).reversed())
                .filter(rule -> matches(invoice.getDescription(), rule))
                .map(CategorizationRule::getCategory)
                .findFirst()
                .orElse(null);
    }

    private boolean matches(String description, CategorizationRule rule) {

        if (description == null) return false;

        return switch (rule.getMatchType()) {
            case "EXACT" -> description.equalsIgnoreCase(rule.getKeyword());
            case "CONTAINS" -> description.toLowerCase().contains(rule.getKeyword().toLowerCase());
            case "REGEX" -> description.matches(rule.getKeyword());
            default -> false;
        };
    }
}

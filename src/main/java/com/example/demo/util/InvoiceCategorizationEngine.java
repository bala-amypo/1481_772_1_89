 package com.example.demo.util;

import com.example.demo.model.*;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class InvoiceCategorizationEngine {

    public Category determineCategory(
            Invoice invoice,
            List<CategorizationRule> rules) {

        String description = invoice.getDescription().toLowerCase();

        return rules.stream()
                .sorted(Comparator.comparing(CategorizationRule::getPriority).reversed())
                .filter(rule -> matchesRule(description, rule))
                .map(CategorizationRule::getCategory)
                .findFirst()
                .orElse(null);
    }

    private boolean matchesRule(String description, CategorizationRule rule) {
        String keyword = rule.getKeyword().toLowerCase();

        switch (rule.getMatchType()) {
            case EXACT:
                return description.equals(keyword);

            case CONTAINS:
                return description.contains(keyword);

            case REGEX:
                return description.matches(keyword);

            default:
                return false;
        }
    }
}

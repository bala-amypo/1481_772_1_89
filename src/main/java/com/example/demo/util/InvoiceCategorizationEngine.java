 package com.example.demo.util;

import com.example.demo.model.Category;
import com.example.demo.model.CategorizationRule;
import com.example.demo.model.Invoice;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class InvoiceCategorizationEngine {

    /**
     * Determines the category for an invoice based on a list of rules.
     *
     * @param invoice The invoice to categorize
     * @param rules   List of rules to apply
     * @return Category assigned by the first matching rule, or null if no match
     */
    public Category determineCategory(Invoice invoice, List<CategorizationRule> rules) {

        if (rules == null || rules.isEmpty()) return null;

        // Sort rules by descending priority
        rules.sort(Comparator.comparing(CategorizationRule::getPriority).reversed());

        String description = invoice.getDescription();
        if (description == null) return null;

        for (CategorizationRule rule : rules) {
            String keyword = rule.getKeyword();
            String matchType = rule.getMatchType();

            switch (matchType) {
                case "EXACT":
                    if (description.equalsIgnoreCase(keyword)) {
                        return rule.getCategory();
                    }
                    break;

                case "CONTAINS":
                    if (description.toLowerCase().contains(keyword.toLowerCase())) {
                        return rule.getCategory();
                    }
                    break;

                case "REGEX":
                    if (Pattern.compile(keyword, Pattern.CASE_INSENSITIVE)
                            .matcher(description).find()) {
                        return rule.getCategory();
                    }
                    break;

                default:
                    // ignore invalid match type
            }
        }

        // No rule matched
        return null;
    }
}

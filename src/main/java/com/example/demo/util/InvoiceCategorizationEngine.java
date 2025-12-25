 package com.example.demo.util;

import com.example.demo.model.Category;
import com.example.demo.model.CategorizationRule;
import com.example.demo.model.Invoice;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

public class InvoiceCategorizationEngine {

    public Category determineCategory(
            Invoice invoice,
            List<CategorizationRule> rules) {

        if (rules == null || rules.isEmpty()) {
            return null;
        }

        // Apply rules in descending priority
        rules.sort(Comparator.comparing(
                CategorizationRule::getPriority).reversed());

        String description = invoice.getDescription();
        if (description == null) {
            return null;
        }

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
                    if (description.toLowerCase()
                            .contains(keyword.toLowerCase())) {
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
        return null;
    }
}

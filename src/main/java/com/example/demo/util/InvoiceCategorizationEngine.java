 package com.example.demo.util;

import com.example.demo.model.Rule;
import com.example.demo.model.Invoice;
import com.example.demo.model.Category;

import java.util.List;

public class InvoiceCategorizationEngine {

    public static Category categorizeInvoice(Invoice invoice, List<Rule> rules) {
        // Apply rules in priority order
        rules.sort((r1, r2) -> Integer.compare(r1.getPriority(), r2.getPriority()));

        for (Rule rule : rules) {
            String type = rule.getRuleType();
            String pattern = rule.getPattern();
            String description = invoice.getDescription();

            if ("EXACT".equalsIgnoreCase(type) && pattern.equals(description)) {
                return rule.getCategory();
            } else if ("CONTAINS".equalsIgnoreCase(type) && description.contains(pattern)) {
                return rule.getCategory();
            } else if ("REGEX".equalsIgnoreCase(type) && description.matches(pattern)) {
                return rule.getCategory();
            }
        }

        return null; // no match
    }
}

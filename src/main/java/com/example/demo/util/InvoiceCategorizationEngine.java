 package com.example.demo.util;

import com.example.demo.model.CategorizationRule;
import com.example.demo.model.Invoice;
import com.example.demo.model.Category;

import java.util.List;
 
public class InvoiceCategorizationEngine {

    public void categorize(Invoice invoice, List<CategorizationRule> rules) {
        String description = invoice.getDescription();

        for (CategorizationRule rule : rules) {
            String pattern = rule.getPattern();
            String type = rule.getRuleType();

            boolean match = false;
            if ("EXACT".equalsIgnoreCase(type)) {
                match = description.equalsIgnoreCase(pattern);
            } else if ("CONTAINS".equalsIgnoreCase(type)) {
                match = description.contains(pattern);
            } else if ("REGEX".equalsIgnoreCase(type)) {
                match = description.matches(pattern);
            }

            if (match) {
                invoice.setCategory(rule.getCategory());
                break; // Stop at first matching rule
            }
        }
    }
}

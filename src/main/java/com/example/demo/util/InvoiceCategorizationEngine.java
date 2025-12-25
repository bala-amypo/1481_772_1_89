 package com.example.demo.util;

import com.example.demo.model.Invoice;
import com.example.demo.model.CategorizationRule;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoiceCategorizationEngine {

    // This method must exactly match the call in InvoiceServiceImpl
    public void categorize(Invoice invoice, List<CategorizationRule> rules) {
        for (CategorizationRule rule : rules) {
            String type = rule.getRuleType();
            String pattern = rule.getPattern();
            String description = invoice.getDescription();

            boolean matched = false;
            if ("EXACT".equalsIgnoreCase(type)) {
                matched = description.equals(pattern);
            } else if ("CONTAINS".equalsIgnoreCase(type)) {
                matched = description.contains(pattern);
            } else if ("REGEX".equalsIgnoreCase(type)) {
                matched = description.matches(pattern);
            }

            if (matched) {
                invoice.setCategory(rule.getCategory());
                break;
            }
        }
    }
}

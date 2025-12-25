 package com.example.demo.util;

import com.example.demo.model.Invoice;
import com.example.demo.model.CategorizationRule;
import java.util.List;
import org.springframework.stereotype.Component;

@Component  // <-- This makes it a Spring Bean
public class InvoiceCategorizationEngine {

    public void applyRules(Invoice invoice, List<CategorizationRule> rules) {
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
                break;  // first match wins
            }
        }
    }
}

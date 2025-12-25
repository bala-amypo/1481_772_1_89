 package com.example.demo.util;

import com.example.demo.model.CategorizationRule;
import com.example.demo.model.Invoice;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoiceCategorizationEngine {

    public void applyRules(Invoice invoice, List<CategorizationRule> rules) {

        for (CategorizationRule rule : rules) {

            String text = invoice.getDescription();

            boolean matched = switch (rule.getMatchType()) {
                case EXACT -> text.equalsIgnoreCase(rule.getKeyword());
                case CONTAINS -> text.toLowerCase().contains(rule.getKeyword().toLowerCase());
                case REGEX -> text.matches(rule.getKeyword());
            };

            if (matched) {
                invoice.setCategory(rule.getCategory());
                break;
            }
        }
    }
}

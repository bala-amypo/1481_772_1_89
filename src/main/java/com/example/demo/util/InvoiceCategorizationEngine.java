 package com.example.demo.engine;

import com.example.demo.model.Invoice;
import com.example.demo.model.Rule;
import com.example.demo.model.Category;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

public class InvoiceCategorizationEngine {

    public void categorizeInvoice(Invoice invoice, List<Rule> rules) {
        // Sort rules by priority ascending (lower number = higher priority)
        rules.sort(Comparator.comparingInt(Rule::getPriority));

        for (Rule rule : rules) {
            String text = invoice.getDescription().toLowerCase();
            String pattern = rule.getPattern().toLowerCase();
            boolean matched = false;

            switch (rule.getRuleType()) {
                case "EXACT":
                    matched = text.equals(pattern);
                    break;
                case "CONTAINS":
                    matched = text.contains(pattern);
                    break;
                case "REGEX":
                    matched = Pattern.compile(rule.getPattern(), Pattern.CASE_INSENSITIVE)
                            .matcher(invoice.getDescription())
                            .find();
                    break;
            }

            if (matched) {
                invoice.setCategory(rule.getCategory());
                return; // Stop at first matched rule (priority applied)
            }
        }

        // No rule matched
        invoice.setCategory(null);
    }
}

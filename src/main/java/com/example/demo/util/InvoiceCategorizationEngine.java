 package com.example.demo.engine;

import com.example.demo.model.Invoice;
import com.example.demo.model.Rule;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

public class InvoiceCategorizationEngine {

    public void categorizeInvoice(Invoice invoice, List<Rule> rules) {
        rules.sort(Comparator.comparingInt(Rule::getPriority)); // priority low -> high

        for (Rule rule : rules) {
            String text = invoice.getDescription().toLowerCase();
            boolean matched = false;

            switch (rule.getRuleType()) {
                case "EXACT":
                    matched = text.equals(rule.getPattern().toLowerCase());
                    break;
                case "CONTAINS":
                    matched = text.contains(rule.getPattern().toLowerCase());
                    break;
                case "REGEX":
                    matched = Pattern.compile(rule.getPattern(), Pattern.CASE_INSENSITIVE)
                            .matcher(invoice.getDescription())
                            .find();
                    break;
            }

            if (matched) {
                invoice.setCategory(rule.getCategory());
                return; // stop at first matched rule
            }
        }

        invoice.setCategory(null); // no match
    }
}

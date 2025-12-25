 package com.example.demo.util;

import com.example.demo.model.CategorizationRule;
import com.example.demo.model.Category;
import com.example.demo.model.Invoice;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Component
public class InvoiceCategorizationEngine {

    /**
     * Apply rules in priority order and return matched Category
     */
    public Category categorize(Invoice invoice, List<CategorizationRule> rules) {

        if (invoice == null || rules == null || rules.isEmpty()) {
            return null;
        }

        // Text used for matching
        String text = invoice.getDescription(); // make sure Invoice has getDescription()
        if (text == null) {
            return null;
        }

        for (CategorizationRule rule : rules) {
            if (rule == null || rule.getPattern() == null) continue;

            String pattern = rule.getPattern();
            String matchType = rule.getMatchType();

            boolean matched = false;

            switch (matchType) {
                case "EXACT":
                    matched = text.equalsIgnoreCase(pattern);
                    break;
                case "CONTAINS":
                    matched = text.toLowerCase().contains(pattern.toLowerCase());
                    break;
                case "REGEX":
                    matched = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE)
                                     .matcher(text)
                                     .find();
                    break;
                default:
                    break;
            }

            if (matched) {
                return rule.getCategory();
            }
        }

        return null;
    }
}

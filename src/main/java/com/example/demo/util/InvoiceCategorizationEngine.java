 package com.example.demo.util;

import com.example.demo.model.CategorizationRule;
import com.example.demo.model.Invoice;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoiceCategorizationEngine {

    public void applyRules(Invoice invoice, List<CategorizationRule> rules) {

        String text = invoice.getInvoiceDescription();

        for (CategorizationRule rule : rules) {

            String type = rule.getMatchType();   // EXACT / CONTAINS / REGEX
            String keyword = rule.getKeyword();

            boolean matched = false;

            if ("EXACT".equalsIgnoreCase(type)) {
                matched = text.equalsIgnoreCase(keyword);
            } else if ("CONTAINS".equalsIgnoreCase(type)) {
                matched = text.toLowerCase().contains(keyword.toLowerCase());
            } else if ("REGEX".equalsIgnoreCase(type)) {
                matched = text.matches(keyword);
            }

            if (matched) {
                invoice.setCategory(rule.getCategory());
                break;
            }
        }
    }
}

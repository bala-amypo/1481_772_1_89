 package com.example.demo.util;

import com.example.demo.model.Invoice;
import com.example.demo.model.Rule;
import com.example.demo.model.Category;

import java.util.List;

public class InvoiceCategorizationEngine {

    public Category categorize(Invoice invoice, List<Rule> rules) {
        for (Rule rule : rules) {
            if (rule.matches(invoice.getDescription())) {
                return rule.getCategory();
            }
        }
        return null; // default if no rule matches
    }
}

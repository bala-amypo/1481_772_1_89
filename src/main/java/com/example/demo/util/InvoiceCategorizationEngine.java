 package com.example.demo.util;

import com.example.demo.model.Invoice;
import com.example.demo.model.Rule;
import com.example.demo.model.Category;

import java.util.List;

public class InvoiceCategorizationEngine {

    public Category categorize(Invoice invoice, List<Rule> rules) {
        // Example logic: return first matching category
        for (Rule rule : rules) {
            if (rule.matches(invoice.getDescription())) { // implement matches() in Rule
                return rule.getCategory();
            }
        }
        return null; // or default category
    }
}

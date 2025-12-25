 package com.example.demo.util;

import com.example.demo.model.Invoice;
import com.example.demo.model.Rule;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoiceCategorizationEngine {

    public void applyRules(Invoice invoice, List<Rule> rules) {

        for (Rule rule : rules) {

            String text = invoice.getDescription();

            if (rule.matches(text)) {
                invoice.setCategory(rule.getCategory());
                return; // first matching rule wins
            }
        }
    }
}

package com.example.demo.util;

import com.example.demo.entity.CategorizationRule;
import com.example.demo.entity.Invoice;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class InvoiceCategorizationEngine {

    public Invoice determineCategory(Invoice invoice, List<CategorizationRule> rules) {
        // For Review-1, just return invoice without categorization
        return invoice;
    }
}

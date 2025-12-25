 import com.example.demo.util.InvoiceCategorizationEngine;

public class InvoiceServiceImpl {

    public void categorizeInvoice(Invoice invoice, List<Rule> rules) {
        Category category = InvoiceCategorizationEngine.categorizeInvoice(invoice, rules);
        invoice.setCategory(category);
    }
}

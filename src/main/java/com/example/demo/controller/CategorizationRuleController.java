 package com.example.demo.controller;

import com.example.demo.model.CategorizationRule;
import com.example.demo.service.CategorizationRuleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
 
 
 @RestController
@RequestMapping("/api/rules")
@Tag(name = "Categorization Rules Endpoints")
public class CategorizationRuleController {

    private final CategorizationRuleService service;

    public CategorizationRuleController(CategorizationRuleService service) {
        this.service = service;
    }

    @PostMapping("/category/{id}")
    public CategorizationRule create(@PathVariable Long id,
                                     @RequestBody CategorizationRule rule) {
        return service.createRule(id, rule);
    }

    @GetMapping("/category/{id}")
    public List<CategorizationRule> list(@PathVariable Long id) {
        return service.getRulesByCategory(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteRule(id);
    }
}

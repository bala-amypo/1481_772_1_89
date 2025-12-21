package com.example.demo.controller;

import com.example.demo.model.CategorizationRule;
import com.example.demo.service.CategorizationRuleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
@Tag(name = "Categorization Rules Endpoints")
public class CategorizationRuleController {

    private final CategorizationRuleService ruleService;

    public CategorizationRuleController(CategorizationRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping("/category/{categoryId}")
    public CategorizationRule createRule(
            @PathVariable Long categoryId,
            @Valid @RequestBody CategorizationRule rule) {

        return ruleService.createRule(categoryId, rule);
    }

    @GetMapping("/category/{categoryId}")
    public List<CategorizationRule> getRules(@PathVariable Long categoryId) {
        return ruleService.getRulesByCategory(categoryId);
    }

    @DeleteMapping("/{ruleId}")
    public void delete(@PathVariable Long ruleId) {
        ruleService.deleteRule(ruleId);
    }
}

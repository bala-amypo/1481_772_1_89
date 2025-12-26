 package com.example.demo.controller;

import com.example.demo.model.CategorizationRule;
import com.example.demo.service.CategorizationRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class CategorizationRuleController {

    private final CategorizationRuleService ruleService;

    public CategorizationRuleController(CategorizationRuleService ruleService){
        this.ruleService = ruleService;
    }

    @PostMapping
    public ResponseEntity<CategorizationRule> createRule(@RequestBody CategorizationRule rule){
        return ResponseEntity.ok(ruleService.createRule(rule));
    }

    @GetMapping
    public ResponseEntity<List<CategorizationRule>> getAllRules(){
        return ResponseEntity.ok(ruleService.getAllRules());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategorizationRule> getRuleById(@PathVariable Long id){
        return ResponseEntity.ok(ruleService.getRuleById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategorizationRule> updateRule(@PathVariable Long id, @RequestBody CategorizationRule rule){
        return ResponseEntity.ok(ruleService.updateRule(id, rule));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRule(@PathVariable Long id){
        ruleService.deleteRule(id);
        return ResponseEntity.ok("Rule deleted successfully");
    }
}

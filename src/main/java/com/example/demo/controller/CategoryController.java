package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Categories Endpoints")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public Category create(@Valid @RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @GetMapping
    public List<Category> list() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category get(@PathVariable Long id) {
        return categoryService.getCategory(id);
    }
}

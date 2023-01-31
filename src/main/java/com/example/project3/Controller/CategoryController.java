package com.example.project3.Controller;

import com.example.project3.Moudle.Category;
import com.example.project3.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/get")
    public ResponseEntity getAllCategories() {
        return ResponseEntity.status(200).body(categoryService.getAllCategories());
    }

    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody @Valid Category category, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body("Category: " + category.getName() + " was added.");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@RequestBody @Valid Category category, @PathVariable String id, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        ResponseEntity result = categoryService.updateCategory(category, id);
        return ResponseEntity.status(result.getStatusCode()).body(result.getBody());
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable String id) {
        ResponseEntity result = categoryService.deleteCategory(id);
        return ResponseEntity.status(result.getStatusCode()).body(result.getBody());
    }

}
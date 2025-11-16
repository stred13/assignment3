package com.example.assignment.controller;

import com.example.assignment.dto.CategoryDTO;
import com.example.assignment.model.Category;
import com.example.assignment.service.CategoryService;
import com.example.assignment.service.RestClientConnect;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    RestClientConnect restClientConnect;

    @Hidden
    @PostMapping("/add-new-category")
    public Category addNewCategory(@RequestBody CategoryDTO category){
        return categoryService.addNewCategory(category);
    }

    @GetMapping("/{database-number}/get-all-categories")
    public List<Category> getAllCategories(@PathVariable("database-number") Integer databaseNumber){
        if (databaseNumber == 1){
            return categoryService.getAllCategories();
        }
        return restClientConnect.getAllCategory(databaseNumber);
    }

    @Hidden
    @GetMapping("/get-category/{category-id}")
    public Category getCategory(@PathVariable("category-id") Integer categoryId){
        return categoryService.getCategoryById(categoryId);
    }

    @PutMapping("/{database-number}/udpate-category/{category-id}")
    public void updateCategory(
            @PathVariable("database-number") Integer databaseNumber,
            @PathVariable("category-id")Integer categoryId,
            @RequestBody String name){
        if (databaseNumber == 1){
            categoryService.updateCategory(categoryId,name);
            return;
        }
        restClientConnect.updateCategory(databaseNumber,categoryId,name);
    }


}

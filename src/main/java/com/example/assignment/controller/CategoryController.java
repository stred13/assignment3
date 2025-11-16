package com.example.assignment.controller;

import com.example.assignment.dto.CategoryDTO;
import com.example.assignment.model.Category;
import com.example.assignment.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/add-new-category")
    public Category addNewCategory(@RequestBody CategoryDTO category){
        return categoryService.addNewCategory(category);
    }

    @GetMapping("/get-all-categories")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/get-category/{category-id}")
    public Category getCategory(@PathVariable("category-id") Integer categoryId){
        return categoryService.getCategoryById(categoryId);
    }

    @PutMapping("/udpate-category/{category-id}")
    public void updateCategory(@PathVariable("category-id")Integer categoryId, @RequestBody String name){
        categoryService.updateCategory(categoryId,name);
    }


}

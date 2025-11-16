package com.example.assignment.service;

import com.example.assignment.dto.CategoryDTO;
import com.example.assignment.model.Category;
import com.example.assignment.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Category addNewCategory(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setName(categoryDTO.getName());
        return categoryRepository.save(category);
    }

    public Category getCategoryById(Integer id){
        return categoryRepository.findById(id).get();
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public void updateCategory(Integer id, String name){
        categoryRepository.findById(id).ifPresent(category -> {
            category.setName(name);
            categoryRepository.save(category);
        });
    }
}

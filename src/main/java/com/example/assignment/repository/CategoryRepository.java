package com.example.assignment.repository;

import com.example.assignment.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    public Category findByNameIgnoreCase(String name);
}

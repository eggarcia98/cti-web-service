package com.ctiwebservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctiwebservice.model.Category;
import com.ctiwebservice.repository.CategoryRepository;


@Service
public class CategoryService {
	
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Method to save a product
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Method to get all products
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Method to delete a product by ID
    public void deleteCategoryById(Long id) {
    	categoryRepository.deleteById(id);
    }
    
}


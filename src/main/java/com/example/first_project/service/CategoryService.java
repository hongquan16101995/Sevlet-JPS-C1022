package com.example.first_project.service;

import com.example.first_project.DAO.CategoryDAO;
import com.example.first_project.model.Category;

import java.util.List;

public class CategoryService {
    private final CategoryDAO categoryDAO;

    public CategoryService() {
        categoryDAO = new CategoryDAO();
    }

    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    public Category findById(Long id) {
        return categoryDAO.findById(id);
    }

    public void createCategory(Category category) {
        categoryDAO.create(category);
    }

    public void updateCategory(Category category) {
        categoryDAO.update(category);
    }

    public void deleteCategory(Long id) {
        categoryDAO.delete(id);
    }
}

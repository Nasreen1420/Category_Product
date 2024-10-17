package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.project.model.Category;
import com.project.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	public Page<Category> getAllCategories(int page, int size) {
		return categoryRepository.findAll(PageRequest.of(page, size));
	}

	public Category getCategoryById(Long id) {
		return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
	}

	public Category createCategory(Category category) {
		return categoryRepository.save(category);
	}

	public Category updateCategory(Long id, Category categoryDetails) {
		Category category = getCategoryById(id);
		category.setName(categoryDetails.getName());
		return categoryRepository.save(category);
	}

	public void deleteCategory(Long id) {
		Category category = getCategoryById(id);
		categoryRepository.delete(category);
	}

}

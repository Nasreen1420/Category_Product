package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.project.model.Category;
import com.project.model.Product;
import com.project.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryService categoryService;

	public Page<Product> getAllProducts(int page, int size) {
		return productRepository.findAll(PageRequest.of(page, size));
	}

	public Product getProductById(Long id) {
		return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
	}

	public Product createProduct(Product product) {
		Category category = categoryService.getCategoryById(product.getCategory().getId());
		product.setCategory(category);
		return productRepository.save(product);
	}

	public Product updateProduct(Long id, Product productDetails) {
		Product product = getProductById(id);
		product.setName(productDetails.getName());
		product.setPrice(productDetails.getPrice());
		product.setDescription(productDetails.getDescription());
		product.setCategory(categoryService.getCategoryById(productDetails.getCategory().getId()));
		return productRepository.save(product);
	}

	public void deleteProduct(Long id) {
		Product product = getProductById(id);
		productRepository.delete(product);
	}

}

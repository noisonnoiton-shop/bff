package com.skcc.category.controller;

import java.util.List;

// import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.skcc.category.domain.Category;
import com.skcc.category.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
// @XRayEnabled
public class CategoryController {

	private CategoryService categoryService;
	
	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@GetMapping("/categories")
	public List<Category> getAllCategories() {
		return this.categoryService.getAllCategories();
	}
}

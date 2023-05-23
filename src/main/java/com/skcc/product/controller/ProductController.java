package com.skcc.product.controller;

import java.util.List;

// import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.skcc.product.domain.Product;
import com.skcc.product.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
// @XRayEnabled
public class ProductController {

	private ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return this.productService.getAllProducts();
	}

	@GetMapping(value="/products/category/{categoryId}")
	public List<Product> getByCategoryId(@PathVariable long categoryId){
		return this.productService.getByCategoryId(categoryId);
	}
	
	@GetMapping(value="/products/sale")
	public List<Product> getProductOnSale(){
		return this.productService.getProductOnSale();
	}
	
	@GetMapping(value="/products/{id}")
	public Product getById(@PathVariable(value="id") long id) {
		return this.productService.getById(id);
	}
}

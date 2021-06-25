package com.skcc.category.service;

import java.util.Arrays;
import java.util.List;

import com.skcc.category.domain.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CategoryService {
	@Value("${api.product.url}")
	private String apiGatewayUrl;
	
	private RestTemplate restTemplate;
	
	@Autowired
	public CategoryService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public List<Category> getAllCategories(){
		return Arrays.asList(this.restTemplate.getForObject(String.format("%s%s", apiGatewayUrl, "/v1/categories"), Category[].class));
	}
}

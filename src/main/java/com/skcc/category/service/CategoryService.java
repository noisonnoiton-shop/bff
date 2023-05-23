package com.skcc.category.service;

import java.util.Arrays;
import java.util.List;

// import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.skcc.category.domain.Category;
import com.skcc.config.RestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
// @XRayEnabled
public class CategoryService {
	@Value("${api.product.url}")
	private String apiGatewayUrl;

	@Autowired
	RestService<Category[]> restCategoryArrayService;

	public CategoryService() {
	}

	public List<Category> getAllCategories() {
		return Arrays.asList(this.restCategoryArrayService
				.get(String.format("%s%s", apiGatewayUrl, "/v1/categories"), HttpHeaders.EMPTY, Category[].class).getBody());
	}
}

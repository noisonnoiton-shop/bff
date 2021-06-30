package com.skcc.product.service;

import java.util.Arrays;
import java.util.List;

import com.skcc.product.domain.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {

	@Value("${api.product.url}")
	private String apiGatewayUrl;
	
	private RestTemplate restTemplate;
	
	@Autowired
	public ProductService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public List<Product> getAllProducts(){
		return Arrays.asList(this.restTemplate.getForObject(String.format("%s%s", apiGatewayUrl, "/v1/products"), Product[].class));
	}

	public List<Product> getByCategoryId(long categoryId) {
		return Arrays.asList(this.restTemplate.getForObject(String.format("%s%s", apiGatewayUrl, "/v1/products/category/" + categoryId), Product[].class));
	}

	public List<Product> getProductOnSale() {
		return Arrays.asList(this.restTemplate.getForObject(String.format("%s%s", apiGatewayUrl, "/v1/products/sale"), Product[].class));
	}

	public Product getById(long id) {
		return this.restTemplate.getForObject(String.format("%s%s", apiGatewayUrl, "/v1/products/" + id), Product.class);
	}
	
}

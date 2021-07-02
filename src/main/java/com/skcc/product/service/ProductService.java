package com.skcc.product.service;

import java.util.Arrays;
import java.util.List;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.skcc.config.RestService;
import com.skcc.product.domain.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
@XRayEnabled
public class ProductService {

	@Value("${api.product.url}")
	private String apiGatewayUrl;
	
	@Autowired
	private RestService<Product> restProductService;
	@Autowired
	private RestService<Product[]> restProductArrayService;
	
	public ProductService() {}
	
	public List<Product> getAllProducts(){
		return Arrays.asList(this.restProductArrayService.get(String.format("%s%s", apiGatewayUrl, "/v1/products"), HttpHeaders.EMPTY,Product[].class).getBody());
	}

	public List<Product> getByCategoryId(long categoryId) {
		return Arrays.asList(this.restProductArrayService.get(String.format("%s%s", apiGatewayUrl, "/v1/products/category/" + categoryId), HttpHeaders.EMPTY,Product[].class).getBody());
	}

	public List<Product> getProductOnSale() {
		return Arrays.asList(this.restProductArrayService.get(String.format("%s%s", apiGatewayUrl, "/v1/products/sale"), HttpHeaders.EMPTY,Product[].class).getBody());
	}

	public Product getById(long id) {
		return this.restProductService.get(String.format("%s%s", apiGatewayUrl, "/v1/products/" + id), HttpHeaders.EMPTY, Product.class).getBody();
	}
	
}

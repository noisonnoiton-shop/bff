package com.skcc.cart.service;

import java.util.Arrays;
import java.util.List;

import com.skcc.cart.domain.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CartService {

	@Value("${api.cart.url}")
	private String apiGatewayUrl;
	
	private RestTemplate restTemplate;
	
	@Autowired
	public CartService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public boolean addCart(long accountId, Cart cart) throws Exception {
		cart.setAccountId(accountId);

		HttpEntity<Cart> requestEntity = new HttpEntity<Cart>(cart);
		ResponseEntity<Boolean> responseEntity = this.restTemplate.exchange(String.format("%s%s", apiGatewayUrl, "/v1/carts"), HttpMethod.PUT, requestEntity, boolean.class);
		
		if(!responseEntity.getBody()) {
			throw new Exception();
		}
		
		return responseEntity.getBody();
	}
	
	public List<Cart> getCartsByAccountId(long accountId){
		return Arrays.asList(this.restTemplate.getForObject(String.format("%s%s%d", apiGatewayUrl, "/v1/carts/account/", accountId), Cart[].class));
	}
	
	public void deleteCart(long id) {
		this.restTemplate.delete(String.format("%s%s%d", apiGatewayUrl, "/v1/carts/", id));
	}
	
}

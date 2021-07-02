package com.skcc.cart.service;

import java.util.Arrays;
import java.util.List;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.skcc.cart.domain.Cart;
import com.skcc.config.RestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@XRayEnabled
public class CartService {

	@Value("${api.cart.url}")
	private String apiGatewayUrl;

	@Autowired
	private RestService<Cart[]> restCartArrayService;
	@Autowired
	private RestService<Boolean> restBoolService;

	public CartService() {
	}

	public boolean addCart(long accountId, Cart cart) throws Exception {
		cart.setAccountId(accountId);

		ResponseEntity<Boolean> responseEntity = this.restBoolService.put(
				String.format("%s%s", apiGatewayUrl, "/v1/carts"), HttpHeaders.EMPTY, cart, boolean.class);

		if (!responseEntity.getBody()) {
			throw new Exception();
		}

		return responseEntity.getBody();
	}

	public List<Cart> getCartsByAccountId(long accountId) {
		return Arrays.asList(this.restCartArrayService
		.get(String.format("%s%s%d", apiGatewayUrl, "/v1/carts/account/", accountId), HttpHeaders.EMPTY, Cart[].class).getBody());
	}

	public void deleteCart(long id) {
		this.restBoolService.callApiEndpoint(String.format("%s%s%d", apiGatewayUrl, "/v1/carts/", id), HttpMethod.DELETE, HttpHeaders.EMPTY, null, boolean.class);
	}

}

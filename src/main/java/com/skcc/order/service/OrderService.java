package com.skcc.order.service;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.skcc.config.RestService;
import com.skcc.order.domain.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@XRayEnabled
public class OrderService {

	@Value("${api.order.url}")
	private String apiGatewayUrl;

	@Autowired
	private RestService<Boolean> restBoolService;

	public OrderService() {}

	public boolean createOrder(Order order) throws Exception {
		ResponseEntity<Boolean> responseEntity = this.restBoolService
				.put(String.format("%s%s", apiGatewayUrl, "/v1/orders"), HttpHeaders.EMPTY, order, boolean.class);

		if (!responseEntity.getBody()) {
			throw new Exception();
		}

		return responseEntity.getBody();
	}
}

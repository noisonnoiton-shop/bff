package com.skcc.order.controller;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.skcc.order.domain.Order;
import com.skcc.order.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
@XRayEnabled
public class OrderController {
	
	private OrderService orderService;
	
	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@PutMapping(value="/orders")
	public boolean createOrder(@RequestBody Order order) throws Exception {
		return this.orderService.createOrder(order);
	}
}

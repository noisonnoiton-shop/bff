package com.skcc.payment.controller;

import java.util.List;

// import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.skcc.payment.domain.Payment;
import com.skcc.payment.service.PaymentService;

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
public class PaymentController {
	
	private PaymentService paymentService;
	
	@Autowired
	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
	@GetMapping(value="/payments/{accountId}")
	public List<Payment> getPaymentsByAccountId(@PathVariable long accountId) {
		return this.paymentService.getPaymentsByAccountId(accountId);
	}
}

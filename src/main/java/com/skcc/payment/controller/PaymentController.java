package com.skcc.payment.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.skcc.payment.domain.Payment;
import com.skcc.payment.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class PaymentController {
	
	private PaymentService paymentService;
	
	@Autowired
	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
	@GetMapping(value="/payments/{accountId}")
	public String findPaymentByAccountId(@PathVariable long accountId, HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();
		if(session.getAttribute("username") == null) {
			return "sign";
		}
		
		List<Payment> paymentsList = this.paymentService.getPaymentsByAccountId(accountId);
		model.addAttribute(paymentsList);
		
		return "payment";
	}
}

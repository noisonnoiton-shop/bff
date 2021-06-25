package com.skcc.payment.service;

import java.util.Arrays;
import java.util.List;

import com.skcc.payment.domain.Payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentService {

	@Value("${api.payment.url}")
	private String apiGatewayUrl;
	
	private RestTemplate restTemplate;
	
	@Autowired
	public PaymentService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public List<Payment> getPaymentsByAccountId(long accountId){
		return Arrays.asList(this.restTemplate.getForObject(String.format("%s%s%d", apiGatewayUrl, "/v1/payments/", accountId), Payment[].class));
	}
	
	
}

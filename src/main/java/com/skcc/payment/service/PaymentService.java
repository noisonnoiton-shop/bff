package com.skcc.payment.service;

import java.util.Arrays;
import java.util.List;

// import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.skcc.config.RestService;
import com.skcc.payment.domain.Payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
// @XRayEnabled
public class PaymentService {

	@Value("${api.payment.url}")
	private String apiGatewayUrl;

	@Autowired
	private RestService<Payment[]> restPaymentArrayService;

	public PaymentService() {
	}

	public List<Payment> getPaymentsByAccountId(long accountId) {
		return Arrays.asList(this.restPaymentArrayService
				.get(String.format("%s%s%d", apiGatewayUrl, "/v1/payments/", accountId), HttpHeaders.EMPTY, Payment[].class).getBody()
				);
	}

}

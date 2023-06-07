package com.skcc.accountbank.controller;

// import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.skcc.accountbank.domain.AccountBank;
import com.skcc.accountbank.service.AccountBankService;
import com.skcc.config.OtelConfig;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.propagation.TextMapSetter;

import java.net.HttpURLConnection;

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
public class AccountBankController {

	private AccountBankService accountBankService;
	// private OtelConfig otelConfig;
	private Tracer tracer;

	@Autowired
	public AccountBankController(
			AccountBankService accountBankService,
			// OtelConfig otelConfig,
			Tracer tracer
		) {
		// this.otelConfig = otelConfig;
		this.tracer = tracer;
		this.accountBankService = accountBankService;
	}

	@GetMapping(value = "/accountbanks/{accountId}")
	public AccountBank getAccountBankByAccountId(@PathVariable long accountId) throws Exception {

		// Tracer tracer = this.otelConfig.openTelemetry().getTracer("accountbank-instrumentation", "1.0.0");
		Span span = tracer.spanBuilder("bff.AccountBankController.findAccountBankByAccountId").startSpan();

		try {
			span.setAttribute("accountId", accountId);

			return this.accountBankService.getAccountBankByAccountId(accountId, span);
		} finally {
			span.end();
		}

		// return this.accountBankService.getAccountBankByAccountId(accountId);
	}

}

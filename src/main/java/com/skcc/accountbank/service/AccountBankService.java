package com.skcc.accountbank.service;

// import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.skcc.accountbank.domain.AccountBank;
import com.skcc.config.OtelConfig;
import com.skcc.config.RestService;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import io.opentelemetry.context.propagation.TextMapSetter;
import io.opentelemetry.semconv.trace.attributes.SemanticAttributes;

import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
// @XRayEnabled
public class AccountBankService {

	@Value("${api.account.url}")
	private String apiGatewayUrl;

	// @Autowired
	// private RestService<AccountBank> restAccountBankService;

	// private OtelConfig otelConfig;
	private Tracer tracer;
	private RestService<AccountBank> restAccountBankService;

	@Autowired
	public AccountBankService(
		// OtelConfig otelConfig,
		Tracer tracer,
		RestService<AccountBank> restAccountBankService
	) {
		// this.otelConfig = otelConfig;
		this.tracer = tracer;
		this.restAccountBankService = restAccountBankService;
	}

	// TextMapSetter<HttpURLConnection> setter = new TextMapSetter<HttpURLConnection>() {
	// 	@Override
	// 	public void set(HttpURLConnection carrier, String key, String value) {
	// 		// Insert the context as Header
	// 		carrier.setRequestProperty(key, value);
	// 	}
	// };

	public AccountBank getAccountBankByAccountId(long accountId, Span parentSpan) throws Exception {

		// URL url = new URL("http://127.0.0.1:8180/v1/accountbanks/1");
		
		// Tracer tracer = this.otelConfig.openTelemetry().getTracer("accountbank-instrumentation", "1.0.0");
		Span span = tracer.spanBuilder("bff.AccountBankService.findAccountBankByAccountId")
				.setSpanKind(SpanKind.CLIENT)
				.setParent(Context.current().with(parentSpan))
				.startSpan();
		
		try(Scope scope = span.makeCurrent()) {
			span.setAttribute(SemanticAttributes.HTTP_METHOD, "GET");
			span.setAttribute("accountId", accountId);
			
			// HttpURLConnection transportLayer = (HttpURLConnection) url.openConnection();
			// this.otelConfig.openTelemetry().getPropagators().getTextMapPropagator().inject(Context.current(),
			// transportLayer, setter);

			// return this.restAccountBankService
			// 		.get(String.format("%s%s%d", "http://127.0.0.1:8180", "/v1/accountbanks/", accountId), HttpHeaders.EMPTY,
			// 				AccountBank.class)
			// 		.getBody();
			return this.restAccountBankService
					.get(String.format("%s%s%d", apiGatewayUrl, "/v1/accountbanks/", accountId), HttpHeaders.EMPTY,
							AccountBank.class)
					.getBody();
		} finally {
			span.end();
		}
		// return this.restAccountBankService.get(String.format("%s%s%d", apiGatewayUrl,
		// "/v1/accountbanks/", accountId), HttpHeaders.EMPTY,
		// AccountBank.class).getBody();
	}
}

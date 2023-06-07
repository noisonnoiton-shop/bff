package com.skcc.config;

import java.util.Collections;

// import com.amazonaws.xray.proxies.apache.http.HttpClientBuilder;

// import org.apache.http.impl.client.CloseableHttpClient;
// import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.skcc.filter.RestTemplateHeaderModifierInterceptor;

@Configuration
public class RestTemplateConfig {

	@Bean
	public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors( Collections.singletonList(new RestTemplateHeaderModifierInterceptor()) );
        return restTemplate;
		// return new RestTemplate();
		// return new RestTemplate(clientHttpRequestFactory());
	}

	// private HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
	// 	PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
	// 	manager.setDefaultMaxPerRoute(500);
	// 	manager.setMaxTotal(50);

	// 	HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
	// 	CloseableHttpClient httpClient = HttpClientBuilder.create().setConnectionManager(manager).build();
	// 	factory.setHttpClient(httpClient);
	// 	return factory;
	// }
	
}
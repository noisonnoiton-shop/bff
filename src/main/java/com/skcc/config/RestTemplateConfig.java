package com.skcc.config;

import com.amazonaws.xray.proxies.apache.http.HttpClientBuilder;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate(clientHttpRequestFactory());
	}

	private HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
		PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
		manager.setDefaultMaxPerRoute(500);
		manager.setMaxTotal(50);

		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		CloseableHttpClient httpClient = HttpClientBuilder.create().setConnectionManager(manager).build();
		factory.setHttpClient(httpClient);
		return factory;
	}
	
}
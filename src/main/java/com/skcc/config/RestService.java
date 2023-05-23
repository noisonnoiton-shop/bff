package com.skcc.config;

// import com.amazonaws.xray.spring.aop.XRayEnabled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
// @XRayEnabled
public class RestService<T> {

  private RestTemplate restTemplate;

	@Autowired
	public RestService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<T> get(String url, HttpHeaders httpHeaders) {
		return callApiEndpoint(url, HttpMethod.GET, httpHeaders, null, (Class<T>) Object.class);
	}

	public ResponseEntity<T> get(String url, HttpHeaders httpHeaders, Class<T> clazz) {
		return callApiEndpoint(url, HttpMethod.GET, httpHeaders, null, clazz);
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<T> post(String url, HttpHeaders httpHeaders, Object body) {
		return callApiEndpoint(url, HttpMethod.POST, httpHeaders, body, (Class<T>) Object.class);
	}

	public ResponseEntity<T> post(String url, HttpHeaders httpHeaders, Object body, Class<T> clazz) {
		return callApiEndpoint(url, HttpMethod.POST, httpHeaders, body, clazz);
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<T> put(String url, HttpHeaders httpHeaders, Object body) {
		return callApiEndpoint(url, HttpMethod.PUT, httpHeaders, body, (Class<T>) Object.class);
	}

	public ResponseEntity<T> put(String url, HttpHeaders httpHeaders, Object body, Class<T> clazz) {
		return callApiEndpoint(url, HttpMethod.PUT, httpHeaders, body, clazz);
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<T> delete(String url, HttpHeaders httpHeaders, Object body) {
		return callApiEndpoint(url, HttpMethod.DELETE, httpHeaders, body, (Class<T>) Object.class);
	}

	public ResponseEntity<T> delete(String url, HttpHeaders httpHeaders, Object body, Class<T> clazz) {
		return callApiEndpoint(url, HttpMethod.DELETE, httpHeaders, body, clazz);
	}

	public ResponseEntity<T> callApiEndpoint(String url, HttpMethod httpMethod, HttpHeaders httpHeaders, Object body,
			Class<T> clazz) {
		return restTemplate.exchange(url, httpMethod, new HttpEntity<>(body, httpHeaders), clazz);
	}
}
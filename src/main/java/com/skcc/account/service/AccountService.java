package com.skcc.account.service;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.skcc.account.domain.Account;
import com.skcc.config.RestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
@XRayEnabled
public class AccountService {

	@Autowired
	private RestService<Account> restAccountService;
	@Autowired
	private RestService<Boolean> restBoolService;

	@Value("${api.account.url}")
	private String apiGatewayUrl;

	public AccountService() {}

	public Account login(Account account) {
		return restAccountService
				.post(String.format("%s%s", apiGatewayUrl, "/v1/login"), HttpHeaders.EMPTY, account, Account.class).getBody();
	}

	public Account findById(long id) {
		return restAccountService
				.get(String.format("%s%s%d", apiGatewayUrl, "/v1/accounts/", id), HttpHeaders.EMPTY, Account.class).getBody();
	}

	public boolean createAccount(Account account) {
		return restBoolService
				.post(String.format("%s%s", apiGatewayUrl, "/v1/accounts"), HttpHeaders.EMPTY, account, boolean.class)
				.getBody();
	}

}

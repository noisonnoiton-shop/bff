package com.skcc.accountbank.service;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.skcc.accountbank.domain.AccountBank;
import com.skcc.config.RestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
// @XRayEnabled
public class AccountBankService {
	
	@Value("${api.account.url}")
	private String apiGatewayUrl;
	
	@Autowired
	private RestService<AccountBank> restAccountBankService;
	
	public AccountBankService() {}
	
	public AccountBank getAccountBankByAccountId(long accountId) {
		return this.restAccountBankService.get(String.format("%s%s%d", apiGatewayUrl, "/v1/accountbanks/", accountId), HttpHeaders.EMPTY, AccountBank.class).getBody();
	}
}

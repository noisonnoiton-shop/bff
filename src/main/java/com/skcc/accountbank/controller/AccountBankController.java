package com.skcc.accountbank.controller;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.skcc.accountbank.domain.AccountBank;
import com.skcc.accountbank.service.AccountBankService;

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
	
	@Autowired
	public AccountBankController(AccountBankService accountBankService) {
		this.accountBankService = accountBankService;
	}

	@GetMapping(value="/accountbanks/{accountId}")
	public AccountBank getAccountBankByAccountId(@PathVariable long accountId) {
		return this.accountBankService.getAccountBankByAccountId(accountId);
	}
	
}

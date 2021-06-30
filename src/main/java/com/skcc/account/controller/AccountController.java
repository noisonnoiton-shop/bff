package com.skcc.account.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.skcc.account.domain.Account;
import com.skcc.account.service.AccountService;
import com.skcc.config.SessionScope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
@Slf4j
public class AccountController {

	private AccountService accountService;

	@Autowired
	SessionScope sessionScope;
	
	@Autowired
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@PostMapping("/login")
	public Account loginByRedis(HttpServletRequest request
					, @RequestBody Account account) throws Exception {
		
		HttpSession session = request.getSession();

		if (session.getAttribute("username") != null) {
			session.removeAttribute("id");
			session.removeAttribute("username");
			session.removeAttribute("name");
			session.removeAttribute("mobile");
			session.removeAttribute("address");
			session.removeAttribute("scope");
		}

		Account resultAccount = this.accountService.login(account);
		
		if(resultAccount.getUsername() != null) {
			session.setAttribute("id", resultAccount.getId());
			session.setAttribute("username", resultAccount.getUsername());
			session.setAttribute("name", resultAccount.getName());
			session.setAttribute("mobile", resultAccount.getMobile());
			session.setAttribute("address", resultAccount.getAddress());
			session.setAttribute("scope", resultAccount.getScope());
		} else {
			throw new Exception();
		}
		
		return resultAccount;
	}
	
//	@PostMapping("/login")
	public Account login(HttpServletRequest request
					, @RequestBody Account account) throws Exception {
		
		HttpSession session = request.getSession();

		if (session.getAttribute("username") != null) {
			session.removeAttribute("id");
			session.removeAttribute("username");
			session.removeAttribute("name");
			session.removeAttribute("mobile");
			session.removeAttribute("address");
			session.removeAttribute("scope");
		}

		Account resultAccount = this.accountService.login(account);
		
		if(resultAccount.getUsername() != null) {
			session.setAttribute("id", resultAccount.getId());
			session.setAttribute("username", resultAccount.getUsername());
			session.setAttribute("name", resultAccount.getName());
			session.setAttribute("mobile", resultAccount.getMobile());
			session.setAttribute("address", resultAccount.getAddress());
			session.setAttribute("scope", resultAccount.getScope());
		} else {
			throw new Exception();
		}
		
		return resultAccount;
	}
	
	@PostMapping("/accounts")
	public boolean createAccount(HttpServletRequest request, @RequestBody Account account) throws Exception {
		HttpSession session = request.getSession();

		if (session.getAttribute("username") != null) {
			session.removeAttribute("id");
			session.removeAttribute("username");
			session.removeAttribute("name");
			session.removeAttribute("mobile");
			session.removeAttribute("address");
			session.removeAttribute("scope");
		}
		
		if(!this.accountService.createAccount(account)) {
			throw new Exception();
		}
		
		return true;
	}

	@GetMapping("/accounts/session")
	public Account shopingCart(HttpServletRequest request) {

		Account account = new Account();

		HttpSession session = request.getSession();
		if(session.getAttribute("username") == null) {
			log.info("No session !!!!");
			
			account = accountService.findById(1);
			return account;
		}
		
		account.setId((long) session.getAttribute("id"));
		account.setUsername((String) session.getAttribute("username"));
		account.setName((String) session.getAttribute("name"));
		account.setMobile((String) session.getAttribute("mobile"));
		account.setScope((String) session.getAttribute("scope"));
		account.setAddress((String) session.getAttribute("address"));

		return account;
	}
	
}

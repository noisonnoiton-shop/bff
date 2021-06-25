package com.skcc.account.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Account {
	
	private long id;
	private String username;
	private String name;
	private String password;
	private String mobile;
	private String scope;
	private String address;
	private String createdAt;
	
}

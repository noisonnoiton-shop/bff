package com.skcc.accountbank.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountBank {
	
	private long id;
	private long accountId;
	private String bankName;
	private String bankNumber;
	private String active;
	private String createdAt;
		
}

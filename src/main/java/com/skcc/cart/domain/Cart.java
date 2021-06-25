package com.skcc.cart.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cart {
	
	private long id;
	private long accountId;
	private long productId;
	private String productActive;
	private long productQuantity;
	private CartProduct productInfo;
	private String createdAt;
		
}

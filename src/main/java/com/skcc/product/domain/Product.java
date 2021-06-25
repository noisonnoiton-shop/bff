package com.skcc.product.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
	
	private long id;
	private String name;
	private String categoryName;
	private String active;
	private long originalPrice;
	private long salePercentage;
	private long salePrice;
	private long resultPrice;
	private long amount;
	private String img;
	private String createdAt;
	
}

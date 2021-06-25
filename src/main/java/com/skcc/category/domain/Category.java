package com.skcc.category.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category {
	private long id;
	private String name;
	private long priority;
	private String active;
	private String createdAt;
	
}

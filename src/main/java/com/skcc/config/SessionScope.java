package com.skcc.config;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
@Data
@ToString
public class SessionScope implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String username;
	private String name;
	private String mobile;
	private String address;
	private String scope;

}

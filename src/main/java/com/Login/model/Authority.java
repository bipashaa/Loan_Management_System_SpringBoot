package com.Login.model;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String authority;

	public Authority(String authority) {
		super();
		this.authority = authority;
	}

	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.authority;
	}

}

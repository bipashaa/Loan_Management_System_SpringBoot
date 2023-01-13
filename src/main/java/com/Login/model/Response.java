package com.Login.model;

public class Response {

	String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Response(String token) {
		super();
		this.token = token;
	}

}

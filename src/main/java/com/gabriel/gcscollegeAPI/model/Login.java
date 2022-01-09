package com.gabriel.gcscollegeAPI.model;

import javax.validation.constraints.NotBlank;

public class Login {
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String password;
	
	private Token token;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Token getToken() {
		return token;
	}
	public void setToken(Token token) {
		this.token = token;
	}

	
}

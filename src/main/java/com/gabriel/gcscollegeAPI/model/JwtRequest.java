package com.gabriel.gcscollegeAPI.model;

public class JwtRequest {
	
	private String userEmail;
	private String userPasword;
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPasword() {
		return userPasword;
	}
	public void setUserPasword(String userPasword) {
		this.userPasword = userPasword;
	}
	
	

}

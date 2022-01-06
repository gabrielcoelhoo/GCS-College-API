package com.gabriel.gcscollegeAPI.model;

public class JwtResponse {
	
	private Student student;
	private String jwtToken;
	
	
	
	public JwtResponse(Student student, String jwtToken) {
		this.student = student;
		this.jwtToken = jwtToken;
	}
	
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public String getJwtToken() {
		return jwtToken;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	
	

}

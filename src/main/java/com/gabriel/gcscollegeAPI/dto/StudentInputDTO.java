package com.gabriel.gcscollegeAPI.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class StudentInputDTO {
	
	@NotEmpty
	private String name;

	@NotEmpty
	private String surname;

	@NotEmpty
	private String password;

	@NotEmpty
	private String phoneNumber;

	@NotEmpty
	@Email
	private String email;

	@NotEmpty
	private String country;

	@NotEmpty
	private String studentComments;
	
	@NotEmpty(message = "addres cannot be null")
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStudentComments() {
		return studentComments;
	}

	public void setStudentComments(String studentComments) {
		this.studentComments = studentComments;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}

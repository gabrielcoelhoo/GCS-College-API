package com.gabriel.gcscollegeAPI.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


//@Entity
public class Student {
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long id;
	
	private String name;
	private String surname;
	private String password;
	private int phoneNumber;
	private String email;
	private String country;
	private String studentComments;
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
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
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
	
	@Override
	public String toString() {
		return "Student [name=" + name + ", surname=" + surname + ", password=" + password + ", phoneNumber="
				+ phoneNumber + ", email=" + email + ", country=" + country + ", studentComments=" + studentComments
				+ "]";
	}
	
	
	
	
}

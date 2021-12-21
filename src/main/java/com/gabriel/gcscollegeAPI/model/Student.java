package com.gabriel.gcscollegeAPI.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 45)
	private String name;
	
	@Column(nullable = false, length = 45)
	private String surname;
	
	public Long getId() {
		return id;
	}
	
	@Column(nullable = false, length = 64)//64 because it will be encrypted
	private String password;
	
	@Column(nullable = false, length = 45)
	private String phoneNumber;
	
	@Column(nullable = false, unique = true, length = 45)//unique because every person has one
	private String email;
	
	@Column(nullable = false, length = 45)
	private String country;
	
	@Column(nullable = false, length = 45)
	private String studentComments;
	
	@Column(nullable = false, length = 45)
	private String address;
	
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public void setPhoneNumber(String string) {
		this.phoneNumber = string;
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
		return "Student [id=" + id + ", name=" + name + ", surname=" + surname + ", password=" + password
				+ ", phoneNumber=" + phoneNumber + ", email=" + email + ", country=" + country + ", studentComments="
				+ studentComments + ", address=" + address + "]";
	}
	

	
	
	
	
}

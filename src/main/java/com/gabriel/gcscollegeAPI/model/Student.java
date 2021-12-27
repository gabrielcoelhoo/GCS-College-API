package com.gabriel.gcscollegeAPI.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "student")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 45)
	@NotEmpty
	private String name;

	@Column(nullable = false, length = 45)
	@NotEmpty
	private String surname;

	@Column(nullable = false, length = 64) // 64 because it will be encrypted
	@NotEmpty
	private String password;

	@Column(nullable = false, length = 45)
	@NotEmpty
	private String phoneNumber;

	@Column(nullable = false, unique = true, length = 45) // unique because every person has one
	@NotEmpty
	@Email
	private String email;

	@Column(nullable = false, length = 45)
	@NotEmpty
	private String country;

	@Column(nullable = false, length = 45)
	@NotEmpty
	private String studentComments;
	
	@Column(nullable = false, length = 45)
	@NotEmpty(message = "addres cannot be null")
	private String address;

	public Student() {

	}

	public Long getId() {
		return id;
	}

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}

package com.gabriel.gcscollegeAPI.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EnrolmentInput {
	
	@NotBlank
	private String email;
	
	@NotNull
	private Long courseID;
	
	
	private List<Long> extrasServices = new ArrayList<>();
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getCourseID() {
		return courseID;
	}
	public void setCourseID(Long courseID) {
		this.courseID = courseID;
	}
	public List<Long> getExtrasServices() {
		return extrasServices;
	}
	public void setExtrasServices(List<Long> extrasServices) {
		this.extrasServices = extrasServices;
	}
	
	
}

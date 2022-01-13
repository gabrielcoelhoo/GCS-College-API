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
	
	
	private List<ExtraEnrolment> extras = new ArrayList<>();
	
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
<<<<<<< HEAD
	
=======
>>>>>>> 993546fc88a1b5fe520acdb90a2d57303a490c6d
	public List<ExtraEnrolment> getExtras() {
		return extras;
	}
	public void setExtras(List<ExtraEnrolment> extras) {
		this.extras = extras;
	}

	
}

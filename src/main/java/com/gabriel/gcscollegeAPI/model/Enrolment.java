package com.gabriel.gcscollegeAPI.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
public class Enrolment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private Course course;
	
	@OneToOne
	private User user;
	
	@OneToOne
	private Employee employee;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Status status = Status.SENT;
	
	private BigDecimal total;
	
	@OneToMany(mappedBy="enrolment", cascade = CascadeType.MERGE)
	private List<ExtraEnrolment> extras = new ArrayList<>();
	
	public void changeStatus(Status status) {
		this.status = status;
	}
	
	public void sumWithExtras(BigDecimal totalExtras) {
		this.total = this.total.add(totalExtras);
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}


	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public List<ExtraEnrolment> getExtras() {
		return extras;
	}
	public void setExtras(List<ExtraEnrolment> extras) {
		this.extras = extras;
	}
	
	
	
}
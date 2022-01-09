package com.gabriel.gcscollegeAPI.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
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
	
	@ManyToMany
	@JoinTable(name = "enrolment_extra", joinColumns = @JoinColumn(name="enrolment_id"),
	inverseJoinColumns =  @JoinColumn(name="extra_id"))
	private List<Extra> extrasServices = new ArrayList<>();
	
	private BigDecimal total;
	

	public void changeStatus(Status status) {
		this.status = status;
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
	public List<Extra> getExtrasServices() {
		return extrasServices;
	}
	public void setExtrasServices(List<Extra> extrasServices) {
		this.extrasServices = extrasServices;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
}

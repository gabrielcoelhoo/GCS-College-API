package com.gabriel.gcscollegeAPI.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + "]";
	}


	@OneToMany(mappedBy = "employee")
	Set<EmployeeCourse> employeeCourses;
	
//	@ManyToMany
//	(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE }, mappedBy = "employee")
//    private Set<Course> courses = new HashSet<>();
	
	
	//separate betweeen
	//private long id;
	//public constructors
	//mappings
	
	
	

}

package com.gabriel.gcscollegeAPI.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	private Long id;
	
	private Set<EmployeeCourse> employeeCourses = new HashSet<EmployeeCourse>();

	
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
	
	@OneToMany(mappedBy = "employee")
    public Set<EmployeeCourse> getEmployeeCourse() {
        return employeeCourses;
    }

	public void addEmployeeCourse(EmployeeCourse employeeCourse) {
        this.employeeCourses.add(employeeCourse);
    } 

	public void setEmployeeCourses(Set<EmployeeCourse> employeeCourses) {
		this.employeeCourses = employeeCourses;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + "]";
	}
	
	//separate betweeen
	//private long id;
	//public constructors
	//mappings
	
	
	

}

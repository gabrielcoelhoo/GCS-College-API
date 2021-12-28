package com.gabriel.gcscollegeAPI.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "EMPLOYEE_COURSES")
public class EmployeeCourse {
	private long id;
	private Employee employee;
	private Course course;
	
	 // additional fields
    private boolean activated;
    private Date registeredDate;
    
    @Id
    @GeneratedValue
    @Column(name = "EMPLOYEE_COURSE_ID")
    public long getId() {
        return id;
    }
 
    public void setId(long id) {
        this.id = id;
    }
 
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "EMPLOYEE_ID") 
    public Employee getEmployee() {
		return employee;
	}
    
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

 
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "COURSE_ID")
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
 
    public boolean isActivated() {
        return activated;
    }
 
    public void setActivated(boolean activated) {
        this.activated = activated;
    }
 
    @Column(name = "REGISTERED_DATE")
    @Temporal(TemporalType.DATE)
    public Date getRegisteredDate() {
        return registeredDate;
    }
 
    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }
    
	
	

}

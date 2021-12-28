package com.gabriel.gcscollegeAPI.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "course")
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@NotNull
	private LocalDate courseStart;
	
	@Column(nullable = false)
	@NotNull
	private LocalDate courseEnd;
	
	@Column(nullable = false, length = 10)
	@NotNull
	private String period;
	
	@Column(nullable = false, length = 40)
	@NotNull
	private String level;
	
	

	public Course() {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getCourseStart() {
		return courseStart;
	}
	public void setCourseStart(LocalDate courseStart) {
		this.courseStart = courseStart;
	}
	public LocalDate getCourseEnd() {
		return courseEnd;
	}
	public void setCourseEnd(LocalDate courseEnd) {
		this.courseEnd = courseEnd;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", courseStart=" + courseStart + ", courseEnd=" + courseEnd + ", period=" + period
				+ ", level=" + level + "]";
	}
	

	
	

}

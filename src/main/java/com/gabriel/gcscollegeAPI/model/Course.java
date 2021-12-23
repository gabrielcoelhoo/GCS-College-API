package com.gabriel.gcscollegeAPI.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
//trial on rafael pull

@Entity
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
	
	@Column(nullable = false)
	@NotNull
	private int acommodation;
	
	@Column(nullable = false)
	@NotNull
	private boolean hasTranfer;
	

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
	public int getAcommodation() {
		return acommodation;
	}
	public void setAcommodation(int acommodation) {
		this.acommodation = acommodation;
	}
	public boolean isHasTranfer() {
		return hasTranfer;
	}
	public void setHasTranfer(boolean hasTranfer) {
		this.hasTranfer = hasTranfer;
	}
	@Override
	public String toString() {
		return "Course [courseStart=" + courseStart + ", courseEnd=" + courseEnd + ", level=" + level + "]";
	}
	
	

}
